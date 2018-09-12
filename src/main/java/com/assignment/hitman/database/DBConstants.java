package com.assignment.hitman.database;

/**
 * @author ashutoshp
 */
public interface DBConstants {
    
     String PLAYER_INSERT_SQL="INSERT INTO PLAYERS (name, money, health, level, weapon_id) VALUES (?,?,?,?,?)";
}
