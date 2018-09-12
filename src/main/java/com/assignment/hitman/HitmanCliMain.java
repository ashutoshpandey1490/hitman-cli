package com.assignment.hitman;

import com.assignment.hitman.dao.PlayerDao;
import com.assignment.hitman.dao.PlayerDaoImpl;
import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.reader.Reader;
import com.assignment.hitman.reader.ReaderFactory;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.writer.Writer;
import com.assignment.hitman.writer.WriterFactory;

import java.sql.SQLException;

/**
 * @author ashutoshp
 */
public class HitmanCliMain {

    public static void main(String[] args) throws SQLException {
        Writer writer = WriterFactory.getWriter();
        Reader reader = ReaderFactory.getReader();
        DBConfiguration.initializeDB();
        //WeaponDao weaponDao = new WeaponDaoImpl();
        //List<Weapon> weaponList =  weaponDao.getAllWeapons();
        //weaponList.forEach(weapon -> System.out.println(ANSI_RED+weapon.toString()+ANSI_RESET));

        writer.writeInfoMsg(MessageConstants.GAME_START);
        writer.writeInputMsg(MessageConstants.START);
        writer.writeInputMsg(MessageConstants.EXIT);

        // TODO: Need to define way for navigation ??
        int input = reader.readInt();
        switch (input) {
            case 1: {
                writer.writeInputMsg(MessageConstants.CREATE_PLAYER);
                String playerName = reader.readString();
                Player newPlayer = new Player(playerName);
                PlayerDao playerDao = new PlayerDaoImpl();
                playerDao.createNewPlayer(newPlayer);
                writer.writeInfoMsg("Player has been created");
                System.exit(0);
                reader.dispose();
            }
            case 2: {
                System.exit(0);
                reader.dispose();
            }
            default: {
                writer.writeErrorMsg(MessageConstants.INVALID_INPUT);
                System.exit(0);
                reader.dispose();
            }
        }
    }
}
