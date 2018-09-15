package com.assignment.hitman.dao;

import com.assignment.hitman.exception.PlayerAlreadyExistException;
import com.assignment.hitman.vo.Player;

/**
 * Class to perform all DB operations related to player.
 *
 * @author ashutoshp
 */
public interface PlayerDao {
     Player getPlayerByName(String playerName);

     void createNewPlayer(Player player) throws PlayerAlreadyExistException;

     void updateExistingPlayer(Player player);

     Integer getPlayerCount();
}
