package com.assignment.hitman.dao;

import com.assignment.hitman.vo.Player;

import java.sql.SQLException;
import java.util.List;

/** @author ashutoshp */
public interface PlayerDao {
     List<Player> getAllPlayers();

     Player getPlayerByName(String playerName);

     void createNewPlayer(Player player) throws SQLException;

     void updateExistingPlayer(Player player);

     Integer getPlayerCount();
}
