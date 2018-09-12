package com.assignment.hitman;

import com.assignment.hitman.dao.PlayerDao;
import com.assignment.hitman.dao.PlayerDaoImpl;
import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;

import java.sql.SQLException;
import java.util.Scanner;

import static com.assignment.hitman.util.ColorConstants.*;

/**
 * @author ashutoshp
 */
public class HitmanCliMain {

    public static void main(String[] args) throws SQLException {
        DBConfiguration.initializeDB();
        Scanner scan = new Scanner(System.in);
        //WeaponDao weaponDao = new WeaponDaoImpl();
        //List<Weapon> weaponList =  weaponDao.getAllWeapons();
        //weaponList.forEach(weapon -> System.out.println(ANSI_RED+weapon.toString()+ANSI_RESET));

        System.out.println(ANSI_GREEN+MessageConstants.GAME_START+ANSI_RESET);
        System.out.println(ANSI_PURPLE+MessageConstants.START+ANSI_RESET);
        System.out.println(ANSI_PURPLE+MessageConstants.EXIT+ANSI_RESET);

        // TO-DO: Need to define way for navigation ??
        // TO-DO: too much sysouts.. need to think of print and input mechanism
        int input = scan.nextInt();
        switch (input) {
            case 1: {
                System.out.println(ANSI_PURPLE+MessageConstants.CREATE_PLAYER+ANSI_RESET);
                String playerName = scan.next();
                Player newPlayer = new Player(playerName);
                PlayerDao playerDao = new PlayerDaoImpl();
                playerDao.createNewPlayer(newPlayer);
                System.out.println(ANSI_GREEN+"Player has been created"+ANSI_RESET);
                System.exit(0);
            }
            case 2: {
                System.exit(0);
            }
            default: {
                System.out.println(ANSI_RED+MessageConstants.INVALID_INPUT+ANSI_RESET);
                System.exit(0);
            }
        }
    }
}
