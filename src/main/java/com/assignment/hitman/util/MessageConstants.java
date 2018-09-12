package com.assignment.hitman.util;

/**
 * @author ashutoshp
 */
public interface MessageConstants {
    String GAME_START = "Hitman game has started !!!!!!!!";
    String INVALID_INPUT = "Invalid input given. Please read the options carefully";
    String START = "1 - Start journey";
    String CREATE_PLAYER = "Enter the player name:";
    String FIRE = "1 - Fire";
    String EXIT = "2 - Exit";
    String SAVE_PROGRESS = "Do you want to save your progress? Press y/n";
    String START_FIGHT_MSG = "You will have to cross 3 levels to win this game. In first level, you will have basic weapon but \n" +
            "you can change them depending on the money you have. As your level increases, you will get cash reward credited in your \n" +
            "account, so you can buy new weapons. Now, get ready and hit the opponent as fast as possible....!!!";
    String WEAPON_BUY_MSG = "Select the option to buy weapon";
    String INSUFFICIENT_BALANCE = "You don't have sufficient balance. You have only $%s in your account. Please choose appropriate weapon";
    String WEAPON_BUY_SUCC_MSG = "You bought %s. %s dollars has been deducted from your account. Your remaining balance is %s";
    String PLAYER_SAVED = "Player has been saved successfully";
    String PLAYER_HEALTH = "You health right now is %s";
}
