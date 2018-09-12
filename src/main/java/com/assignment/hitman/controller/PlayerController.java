package com.assignment.hitman.controller;

import com.assignment.hitman.dao.PlayerDao;
import com.assignment.hitman.dao.PlayerDaoImpl;
import com.assignment.hitman.dao.WeaponDao;
import com.assignment.hitman.dao.WeaponDaoImpl;
import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.vo.Weapon;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author ashutoshp
 */
public class PlayerController {

    private Writer writer = WriterFactory.getWriter();
    private Reader reader = ReaderFactory.getReader();

    public void createPlayer() throws SQLException {
        writer.writeInputMsg(MessageConstants.CREATE_PLAYER);
        String playerName = reader.readString();
        Player newPlayer = new Player(playerName);
        PlayerDao playerDao = new PlayerDaoImpl();
        playerDao.createNewPlayer(newPlayer);
        writer.writeInfoMsg("Player has been created");
        startJourney(newPlayer);
    }

    public void startJourney(Player player) throws SQLException {
        List<String> journeyOptions = new ArrayList<>();
        journeyOptions.add("Start game");
        journeyOptions.add("Buy new weapon");
        journeyOptions.add("View player");
        journeyOptions.add("Exit");
        IntStream.range(0, journeyOptions.size()).forEach(i -> writer.writeInputMsg(i + 1 + " - " + journeyOptions.get(i)));
        int input = reader.readInt();
        switch (input) {
            case 1: {
                writer.writeInfoMsg(MessageConstants.START_FIGHT_MSG);
                startFight(player);
                System.exit(0);
                break;
            }
            case 2: {
                WeaponController weaponController = new WeaponController();
                weaponController.buyWeapon(player);
                break;
            }
            case 3: {
                viewPlayer(player);
                break;
            }
            case 4: {
                System.exit(0);
                reader.dispose();
                break;
            }
            default: {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                startJourney(player);
            }
        }
    }

    public void startFight(Player player)  {
        writer.writeInputMsg(MessageConstants.FIRE);
        writer.writeInputMsg(MessageConstants.EXIT);
        writer.writeInfoMsg(MessageConstants.PLAYER_HEALTH, player.getHealth());
        int input = reader.readInt();
        switch (input) {
            case 1: {
                //TODO - how to fight?
                while(player.getMoney() != 0) {

                }
                break;
            }
            case 2: {
                writer.writeInputMsg(MessageConstants.SAVE_PROGRESS);
                saveProgress(player);
                break;
            }
            default: {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                startFight(player);
            }
        }
    }

    public void saveProgress(Player player) {
        String answer = reader.readString();
        switch (answer) {
            case "y":{
                PlayerDao playerDao = new PlayerDaoImpl();
                playerDao.updateExistingPlayer(player);
                writer.writeInfoMsg(MessageConstants.PLAYER_SAVED);
                System.exit(0);
            }
            case "n": {
                // TODO - Need to put it in one place so that resources can be cleaned up.
                System.exit(0);
            }
            default: {
                System.exit(0);
            }
        }
    }

    public void viewPlayer(Player player) throws SQLException {
        WeaponDao weaponDao = new WeaponDaoImpl();
        Weapon weapon = weaponDao.getWeaponById(player.getWeaponId());
        player.setCurrentWeapon(weapon);
        writer.writeInfoMsg(player.toString());
        writer.writeInfoMsg("1 - Go back");
        int input = reader.readInt();
        switch (input) {
            case 1: {
                startJourney(player);
                break;
            }
            default: {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                viewPlayer(player);
                break;
            }
        }
    }
}
