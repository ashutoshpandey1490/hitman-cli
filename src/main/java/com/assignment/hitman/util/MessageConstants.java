package com.assignment.hitman.util;

/**
 * Single place to define all application constants.
 *
 * @author ashutoshp
 */
public interface MessageConstants {

    // JDBC driver name and database URL
    String JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:tcp://localhost/~/hitmanDB";

    //  Database credentials
    String USER = "sa";
    String PASS = "";

    String GAME_START = "\n\nHitman game has started !!!!!!!!";
    String INVALID_INPUT = "Invalid input given. Please read the options carefully";
    String START = "Start new journey";
    String RESUME = "Resume game";
    String EXISTING_PLAYER = "Enter the existing player name:";
    String ROLE_PLAYER = "Let us create a Character for you. Enter the new player name:";
    String FIRE = "Fire";
    String EXIT = "Exit";
    String SAVE_PROGRESS = "Do you want to save your progress? Press y/n";
    String START_FIGHT_MSG =
            "There are 3 levels in this game. You will have to cross all 3 levels to win this series. In first level, you will have basic \n"
                    + "weapon but you can change them depending on the money you have. As your level increases, you will get cash reward \n"
                    + "credited in your account, so you can buy new weapons. Now, get ready and hit the opponent as FAST as possible....!!!";
    String WEAPON_BUY_MSG = "Select the option to buy weapon";
    String INSUFFICIENT_BALANCE =
            "You don't have sufficient balance. You have only $%s in your account. Please choose appropriate weapon";
    String WEAPON_BUY_SUCC_MSG =
            "You bought %s. %s dollars have been deducted from your account. Your remaining balance is %s dollars.";
    String PLAYER_SAVED = "Player has been saved successfully";
    String PLAYER_HEALTH = "You health right now is %s";
    String CONTINUE = "Play another game";

    String UPGRADE_LEVEL =
            "Now, you are at level %s. You have won $1000 and it has been credited to your account. You can change \n"
                    + " the weapon for level %s.";
    String WON_SERIES = "Congratulations. You have won the series.";
    String PLAYER_NOT_EXISTS = "Player with the name %s does not exist.";
    String PLAYER_CREATED = "The Player has been created";
    String START_GAME = "Start game";
    String NEW_WEAPON = "Buy new weapon";
    String VIEW_PLAYER = "View player";
    String PLAYER_LOST = "You have lost..";
    String LEVEL_COMPLETED = "You have Won. Level completed.";
    String HIT_THE_ENEMY = "You hit the enemy..Enemy health is now %s";
    String ENEMY_HITS_YOU = "You got hit by the enemy..Your health is now %s";
    String SYSTEM_PLAYER = "SystemPlayer";
    String GO_BACK = "Go back";
    String PLAYER_NOT_CREATED = "Player could not be created";
    String PLAYER_ALREADY_EXISTS = "Player with the name %s already exists.";
    String START_HITTING = "Start";
    String WON_MSG = "You have won";
}
