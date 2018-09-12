package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.database.DBConstants;
import com.assignment.hitman.util.WeaponType;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.vo.Weapon;

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
        Connection conn = DBConfiguration.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DBConstants.PLAYER_INSERT_SQL);
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getMoney());
            stmt.setInt(3, player.getHealth());
            stmt.setInt(4, player.getLevel());
            stmt.setInt(5, player.getWeapon_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            stmt.close();
            conn.close();
        }
    }

    @Override
    public void updateExistingPlayer(Player player) {

    }
}
