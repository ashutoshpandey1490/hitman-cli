package com.assignment.hitman.controller;

import com.assignment.hitman.dao.PlayerDaoImpl;
import com.assignment.hitman.exception.PlayerAlreadyExistException;
import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.state.*;
import com.assignment.hitman.util.GameUtils;
import com.assignment.hitman.util.Level;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/** @author ashutoshp */
public class PlayerController {

    private PlayerController() {}

    private static class PlayerControllerCreator {
        private static final PlayerController INSTANCE = new PlayerController();
    }

    public static PlayerController getInstance() {
        return PlayerControllerCreator.INSTANCE;
    }

    private static final Writer writer = WriterFactory.getWriter();
    private static final Reader reader = ReaderFactory.getReader();
    private AtomicBoolean hasPlayerWon = new AtomicBoolean(false);
    private AtomicBoolean isInterrupted = new AtomicBoolean(false);

    public void createPlayer() throws SQLException {
        writer.writeInputMsg(MessageConstants.ROLE_PLAYER);
        String playerName = reader.readString();
        Player newPlayer = new Player(playerName);
        if (!isPlayerAlreadyExists(newPlayer)) {
            writer.writeInfoMsg(MessageConstants.PLAYER_CREATED);
            startJourney(newPlayer);
        } else {
            GameController.getInstance().resumeGame();
        }
    }

    private boolean isPlayerAlreadyExists(Player newPlayer) throws SQLException {
        Boolean result = false;
        try {
            PlayerDaoImpl.getInstance().createNewPlayer(newPlayer);
        } catch (PlayerAlreadyExistException ex) {
            writer.writeErrorMsg(ex.getMessage());
            result = true;
        }
        return result;
    }

    public void resumeWithPlayer() throws SQLException {
        writer.writeInputMsg(MessageConstants.EXISTING_PLAYER);
        String playerName = reader.readString();
        Player existingPlayer = PlayerDaoImpl.getInstance().getPlayerByName(playerName);
        if (existingPlayer == null) {
            writer.writeErrorMsg(MessageConstants.PLAYER_NOT_EXISTS, playerName);
            GameController.getInstance().resumeGame();
        } else {
            startJourney(existingPlayer);
        }
    }

    public void startJourney(Player player) throws SQLException {
        hasPlayerWon.set(false);
        isInterrupted.set(false);
        GameUtils.printOptions(StartJourney.getValues());
        int input = reader.readInt();
        if (StartJourney.getOptionByKey(input) == null) {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            startJourney(player);
        }
        switch (StartJourney.getOptionByKey(input)) {
            case START_GAME:
            {
                writer.writeInfoMsg(MessageConstants.START_FIGHT_MSG);
                startHitting(player);
                break;
            }
            case NEW_WEAPON:
            {
                WeaponController.getInstance().buyWeapon(player);
                break;
            }
            case VIEW_PLAYER:
            {
                viewPlayer(player);
                break;
            }
            case EXIT:
            {
                writer.writeInputMsg(MessageConstants.SAVE_PROGRESS);
                saveProgress(player, null);
                break;
            }
        }
    }

    private void startHitting(Player player) throws SQLException {
        GameUtils.printOptions(StartHitting.getValues());
        int input = reader.readInt();
        if (StartHitting.getOptionByKey(input) == null) {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            startHitting(player);
        }
        switch (StartHitting.getOptionByKey(input)) {
            case START_HITTING:
            {
                Player systemPlayer = initializeSystemPlayer(player);
                player = initializePlayer(player);
                startSystemPlayerJouney(player, systemPlayer);
                startFight(player, systemPlayer);
                break;
            }
            case EXIT:
            {
                writer.writeInputMsg(MessageConstants.SAVE_PROGRESS);
                saveProgress(player, null);
                break;
            }
        }
    }

    public void startFight(Player player, Player systemPlayer) throws SQLException {
        GameUtils.printOptions(StartFight.getValues());
        writer.writeInfoMsg(MessageConstants.PLAYER_HEALTH, player.getHealth());
        int input = reader.readInt();
        if (StartFight.getOptionByKey(input) == null) {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            startFight(player, systemPlayer);
        }
        switch (StartFight.getOptionByKey(input)) {
            case FIRE:
            {
                if (player.getHealth() <= 0) {
                    writer.writeInfoMsg(MessageConstants.PLAYER_LOST);
                    continueGame(player);
                } else if (systemPlayer.getHealth() <= 0) {
                    writer.writeInfoMsg(MessageConstants.LEVEL_COMPLETED);
                    setHasPlayerWon();
                    upgradeLevel(player);
                } else {
                    int health = systemPlayer.getHealth() - player.getCurrentWeapon().getHitValue();
                    systemPlayer.setHealth(health);
                    writer.writeInfoMsg(MessageConstants.HIT_THE_ENEMY, (health <= 0 ? 0 : health));
                    if (health <= 0) {
                        writer.writeInfoMsg(MessageConstants.LEVEL_COMPLETED);
                        setHasPlayerWon();
                        upgradeLevel(player);
                    } else {
                        startFight(player, systemPlayer);
                    }
                }
                break;
            }
            case EXIT:
            {
                isInterrupted.set(true);
                writer.writeInputMsg(MessageConstants.SAVE_PROGRESS);
                saveProgress(player, systemPlayer);
                break;
            }
        }
    }

    public void startSystemPlayerJouney(Player userPlayer, Player systemPlayer) {
        Runnable runnable =
                () -> {
                    // Initial delay to make it fare for the player
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (userPlayer.getHealth() > 0 && !hasPlayerWon.get() && !isInterrupted.get()) {
                        try {
                            int health = userPlayer.getHealth() - systemPlayer.getCurrentWeapon().getHitValue();
                            writer.writeInfoMsg(MessageConstants.ENEMY_HITS_YOU, (health <= 0 ? 0 : health));
                            userPlayer.setHealth(health);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!hasPlayerWon.get() && !isInterrupted.get()) {
                        writer.writeInfoMsg(MessageConstants.PLAYER_LOST);
                    }
                };
        new Thread(runnable, MessageConstants.SYSTEM_PLAYER).start();
    }

    public void saveProgress(Player player, Player systemPlayer) throws SQLException {
        String answer = reader.readString();
        switch (answer.toLowerCase()) {
            case "y":
            {
                if (systemPlayer != null) {
                    player.setOpponentHealth(systemPlayer.getHealth());
                    player.setOpponentWeaponId(systemPlayer.getOpponentWeaponId());
                }
                PlayerDaoImpl.getInstance().updateExistingPlayer(player);
                writer.writeInfoMsg(MessageConstants.PLAYER_SAVED);
                GameController.getInstance().resumeGame();
            }
            case "n":
            {
                GameController.getInstance().resumeGame();
            }
            default:
            {
                GameUtils.stopGame();
            }
        }
    }

    public void viewPlayer(Player player) throws SQLException {
        player.setCurrentWeapon(WeaponController.getInstance().getWeaponById(player.getWeaponId()));
        writer.writeInfoMsg(player.toString());
        GameUtils.printOptions(ViewPlayer.getValues());
        int input = reader.readInt();
        if (ViewPlayer.getOptionByKey(input) == null) {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            viewPlayer(player);
        }
        switch (ViewPlayer.getOptionByKey(input)) {
            case GO_BACK:
            {
                startJourney(player);
                break;
            }
        }
    }

    public Player initializeSystemPlayer(Player user) throws SQLException {
        Player systemPlayer = new Player(MessageConstants.SYSTEM_PLAYER);
        // Both must be at the same level
        systemPlayer.setLevel(user.getLevel());
        WeaponController weaponController = WeaponController.getInstance();
        if (user.getOpponentHealth() != null && user.getOpponentHealth() != 0) {
            systemPlayer.setHealth(user.getOpponentHealth());
            systemPlayer.setWeaponId(user.getWeaponId());
            systemPlayer.setCurrentWeapon(weaponController.getSystemWeapon(user));
        } else {
            systemPlayer.setCurrentWeapon(weaponController.getSystemWeapon(user));
        }
        return systemPlayer;
    }

    public Player initializePlayer(Player player) throws SQLException {
        // If player is not having weaponData
        if (player.getCurrentWeapon() == null) {
            player.setCurrentWeapon(WeaponController.getInstance().getWeaponById(player.getWeaponId()));
        }
        return player;
    }

    public void continueGame(Player player) throws SQLException {
        GameUtils.printOptions(ContinueGame.getValues());
        Integer input = reader.readInt();
        if (ContinueGame.getOptionByKey(input) == null) {
            writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
            continueGame(player);
        }
        switch (ContinueGame.getOptionByKey(input)) {
            case CONTINUE:
            {
                Player newPlayer =
                        new Player(player.getName())
                                .setId(player.getId())
                                .setCurrentWeapon(player.getCurrentWeapon());

                startJourney(newPlayer);
                break;
            }
            case EXIT:
            {
                GameUtils.stopGame();
                break;
            }
        }
    }

    public void upgradeLevel(Player player) throws SQLException {
        if (player.getLevel() == 3) {
            writer.writeInfoMsg(MessageConstants.WON_SERIES);
            GameUtils.stopGame();
        }
        Level level = Level.getLevelFromValue(player.getLevel() + 1);
        player.setLevel(level.getValue());
        player.setMoney(player.getMoney() + level.getMoney());
        player.setWeaponId(level.getDefaultWeaponId());
        player.setHealth(100);
        writer.writeInfoMsg(MessageConstants.UPGRADE_LEVEL, level.getValue(), level.getValue());
        startJourney(player);
    }

    private void setHasPlayerWon() {
        hasPlayerWon.set(true);
        try {
            // Giving time to stop system player
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getCountOfPlayer() {
        return PlayerDaoImpl.getInstance().getPlayerCount();
    }
}
