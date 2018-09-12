package com.assignment.hitman.database;

/**
 * @author ashutoshp
 */
public interface DBConstants {
    
     String PLAYER_INSERT_SQL="INSERT INTO PLAYERS (NAME, MONEY, HEALTH, LEVEL, WEAPON_ID) VALUES (?,?,?,?,?)";
     String PLAYER_UPDATE_SQL = "UPDATE PLAYERS SET NAME=?, MONEY=?, HEALTH=?, LEVEL=?, WEAPON_ID=? WHERE ID=?";
     String GET_WEAPON_SQL = "SELECT * FROM WEAPONS WHERE ID=?";
     String GET_ALL_WEAPON_SQL = "SELECT * FROM WEAPONS";
}
