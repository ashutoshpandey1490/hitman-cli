package com.assignment.hitman.controller;

import com.assignment.hitman.dao.PlayerDao;
import com.assignment.hitman.dao.PlayerDaoImpl;
import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.util.GameUtils;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/** @author ashutoshp */
public class PlayerController {

    private static final Writer writer = WriterFactory.getWriter();
    private static final Reader reader = ReaderFactory.getReader();
    // TODO- should it be AtomicBoolean?
    private volatile boolean hasPlayerWon = false;
    private AtomicBoolean isInterrupted = new AtomicBoolean(false);

    public void createPlayer() throws SQLException {
        writer.writeInputMsg(MessageConstants.CREATE_PLAYER);
        String playerName = reader.readString();
        Player newPlayer = new Player(playerName);
        PlayerDao playerDao = new PlayerDaoImpl();
        playerDao.createNewPlayer(newPlayer);
        writer.writeInfoMsg("Player has been created");
        startJourney(newPlayer);
    }

    public void resumeWithPlayer() throws SQLException {
        writer.writeInputMsg(MessageConstants.CREATE_PLAYER);
        String playerName = reader.readString();
        PlayerDao playerDao = new PlayerDaoImpl();
        Player existingPlayer = playerDao.getPlayerByName(playerName);
        if (existingPlayer == null) {
            writer.writeErrorMsg(MessageConstants.PLAYER_NOT_EXISTS, playerName);
            GameController gameController = new GameController();
            gameController.resumeGame();
        } else {
            startJourney(existingPlayer);
        }
    }

    public void startJourney(Player player) throws SQLException {
        hasPlayerWon = false;
        List<String> journeyOptions = new ArrayList<>();
        journeyOptions.add("Start game");
        journeyOptions.add("Buy new weapon");
        journeyOptions.add("View player");
        journeyOptions.add("Exit");
        IntStream.range(0, journeyOptions.size())
                .forEach(i -> writer.writeInputMsg(i + 1 + " - " + journeyOptions.get(i)));
        int input = reader.readInt();
        switch (input) {
            case 1:
            {
                writer.writeInfoMsg(MessageConstants.START_FIGHT_MSG);
                Player systemPlayer = initializeSystemPlayer(player);
                player = initializePlayer(player);
                startSystemPlayerJouney(player, systemPlayer);
                startFight(player, systemPlayer);
                break;
            }
            case 2:
            {
                WeaponController weaponController = new WeaponController();
                weaponController.buyWeapon(player);
                break;
            }
            case 3:
            {
                viewPlayer(player);
                break;
            }
            case 4:
            {
                writer.writeInputMsg(MessageConstants.SAVE_PROGRESS);
                saveProgress(player, null);
                break;
            }
            default:
            {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                startJourney(player);
            }
        }
    }

    public void startFight(Player player, Player systemPlayer) throws SQLException {
        writer.writeInputMsg(MessageConstants.FIRE);
        writer.writeInputMsg(MessageConstants.EXIT);
        writer.writeInfoMsg(MessageConstants.PLAYER_HEALTH, player.getHealth());
        int input = reader.readInt();
        switch (input) {
            case 1:
            {
                if (player.getHealth() <= 0) {
                    writer.writeInfoMsg("You have lost..");
                    continueGame(player);
                } else if (systemPlayer.getHealth() <= 0) {
                    writer.writeInfoMsg("You have Won. Level completed.");
                    setHasPlayerWon();
                    upgradeLevel(player);
                } else {
                    int health = systemPlayer.getHealth() - player.getCurrentWeapon().getHitValue();
                    systemPlayer.setHealth(health);
                    writer.writeInfoMsg(
                            "You hit the enemy..Enemy health is now " + (health <= 0 ? 0 : health));
                    if (health <= 0) {
                        writer.writeInfoMsg("You have Won. Level completed.");
                        setHasPlayerWon();
                        upgradeLevel(player);
                    } else {
                        startFight(player, systemPlayer);
                    }
                }
                break;
            }
            case 2:
            {
                isInterrupted.set(true);
                writer.writeInputMsg(MessageConstants.SAVE_PROGRESS);
                saveProgress(player, systemPlayer);
                break;
            }
            default:
            {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                startFight(player, systemPlayer);
            }
        }
    }

    public void startSystemPlayerJouney(Player userPlayer, Player systemPlayer) {
        Runnable runnable =
                () -> {
                    // Initial delay because player/user must have some time to read instructions
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (userPlayer.getHealth() > 0 && !hasPlayerWon && !isInterrupted.get()) {
                        try {
                            int health = userPlayer.getHealth() - systemPlayer.getCurrentWeapon().getHitValue();
                            writer.writeInfoMsg(
                                    "You got hit by the enemy..Health is now " + (health <= 0 ? 0 : health));
                            userPlayer.setHealth(health);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!hasPlayerWon && !isInterrupted.get()) {
                        writer.writeInfoMsg("You have lost..");
                    }
                };
        // ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        // pool.schedule(runnable, 2, TimeUnit.SECONDS);
        new Thread(runnable, "SystemPlayer").start();
    }

    public void saveProgress(Player player, Player systemPlayer) throws SQLException {
        String answer = reader.readString();
        switch (answer.toLowerCase()) {
            case "y":
            {
                PlayerDao playerDao = new PlayerDaoImpl();
                if (systemPlayer != null) {
                    player.setOpponentHealth(systemPlayer.getHealth());
                    player.setOpponentWeaponId(systemPlayer.getOpponentWeaponId());
                }
                playerDao.updateExistingPlayer(player);
                writer.writeInfoMsg(MessageConstants.PLAYER_SAVED);
                GameController gameController = new GameController();
                gameController.resumeGame();
            }
            case "n":
            {
                GameUtils.stopGame();
            }
            default:
            {
                GameUtils.stopGame();
            }
        }
    }

    public void viewPlayer(Player player) throws SQLException {
        WeaponController weaponController = new WeaponController();
        player.setCurrentWeapon(weaponController.getWeaponById(player.getWeaponId()));
        writer.writeInfoMsg(player.toString());
        writer.writeInputMsg("1 - Go back");
        int input = reader.readInt();
        switch (input) {
            case 1:
            {
                startJourney(player);
                break;
            }
            default:
            {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                viewPlayer(player);
                break;
            }
        }
    }

    public Player initializeSystemPlayer(Player user) throws SQLException {
        Player systemPlayer = new Player("system");
        // Both must be at the same level
        systemPlayer.setLevel(user.getLevel());
        if(user.getOpponentHealth() != null && user.getOpponentHealth() != 0) {
            systemPlayer.setHealth(user.getOpponentHealth());
            systemPlayer.setWeaponId(user.getWeaponId());
            WeaponController controller = new WeaponController();
            systemPlayer.setCurrentWeapon(controller.getSystemWeapon(user));
        } else {
            WeaponController controller = new WeaponController();
            systemPlayer.setCurrentWeapon(controller.getSystemWeapon(user));
        }
        return systemPlayer;
    }

    public Player initializePlayer(Player player) throws SQLException {
        // If player is not having weaponData
        if (player.getCurrentWeapon() == null) {
            WeaponController weaponController = new WeaponController();
            player.setCurrentWeapon(weaponController.getWeaponById(player.getWeaponId()));
        }
        return player;
    }

    public void continueGame(Player player) throws SQLException {
        writer.writeInputMsg(MessageConstants.CONTINUE);
        writer.writeInputMsg(MessageConstants.EXIT);
        Integer input = reader.readInt();
        switch (input) {
            case 1:
            {
                Player newPlayer =
                        new Player(player.getName())
                                .setId(player.getId())
                                .setCurrentWeapon(player.getCurrentWeapon());

                startJourney(newPlayer);
            }
            case 2:
            {
                GameUtils.stopGame();
            }
            default:
            {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                continueGame(player);
            }
        }
    }

    public void upgradeLevel(Player player) throws SQLException {
        if (player.getLevel() == 3) {
            writer.writeInfoMsg(MessageConstants.WON_SERIES);
            GameUtils.stopGame();
        }
        // TODO - can level be an enum with all the information?
        Integer newLevel = player.getLevel() + 1;
        Integer money = player.getMoney() + 1000;
        player.setLevel(newLevel);
        player.setMoney(money);
        player.setHealth(100);
        writer.writeInfoMsg(MessageConstants.UPGRADE_LEVEL, newLevel, newLevel);
        startJourney(player);
    }

    private void setHasPlayerWon() {
        hasPlayerWon = true;
        try {
            // Giving time to stop system player
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getCountOfPlayer() {
        PlayerDao playerDao = new PlayerDaoImpl();
        return playerDao.getPlayerCount();
    }
}
