package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.database.DBConstants;
import com.assignment.hitman.vo.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ashutoshp
 */
public class PlayerDaoImpl implements PlayerDao {


    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Override
    public Player getPlayerByName() {
        return null;
    }

    @Override
    public void createNewPlayer(Player player) throws SQLException {
        try(Connection conn = DBConfiguration.getConnection();
            PreparedStatement stmt = conn.prepareStatement(DBConstants.PLAYER_INSERT_SQL);) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getMoney());
            stmt.setInt(3, player.getHealth());
            stmt.setInt(4, player.getLevel());
            stmt.setInt(5, player.getWeaponId());
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Player could not be created");
            }
            try(ResultSet resultSet = stmt.getGeneratedKeys()) {
                if(resultSet.next()){
                    player.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateExistingPlayer(Player player) {
        try (Connection conn = DBConfiguration.getConnection();
                        PreparedStatement stmt = conn.prepareStatement(DBConstants.PLAYER_UPDATE_SQL);){
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getMoney());
            stmt.setInt(3, player.getHealth());
            stmt.setInt(4, player.getLevel());
            stmt.setInt(5, player.getWeaponId());
            stmt.setInt(6, player.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
