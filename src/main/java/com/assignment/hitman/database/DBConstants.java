package com.assignment.hitman.database;

/**
 * Single place to define all sql queries.
 *
 * @author ashutoshp
 */
public interface DBConstants {

     String PLAYER_INSERT_SQL =
             "INSERT INTO PLAYERS (NAME, MONEY, HEALTH, LEVEL, WEAPON_ID) VALUES (?,?,?,?,?)";
     String PLAYER_UPDATE_SQL =
             "UPDATE PLAYERS SET NAME=?, MONEY=?, HEALTH=?, LEVEL=?, WEAPON_ID=?, OPPONENT_HEALTH=?, OPPONENT_WEAPON_ID=? WHERE ID=?";
     String GET_WEAPON_SQL = "SELECT * FROM WEAPONS WHERE ID=?";
     String GET_PLAYER_SQL = "SELECT * FROM PLAYERS WHERE NAME=?";
     String GET_ALL_WEAPON_SQL = "SELECT * FROM WEAPONS";
     String GET_WEAPON_LEVEL_SQL = "SELECT * FROM WEAPONS WHERE LEVEL=?";
     String GET_PLAYER_COUNT = "SELECT COUNT(*) AS PLAYERCOUNT FROM PLAYERS";
}
