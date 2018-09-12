package com.assignment.hitman.dao;

import com.assignment.hitman.vo.Player;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ashutoshp
 */
public interface PlayerDao {
    public List<Player> getAllPlayers();
    public Player getPlayerByName();
    public void createNewPlayer(Player player) throws SQLException;
    public void updateExistingPlayer(Player player);
}
