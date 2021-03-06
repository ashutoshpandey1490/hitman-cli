package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.database.DBConstants;
import com.assignment.hitman.exception.PlayerAlreadyExistException;
import com.assignment.hitman.util.MessageConstants;
import com.assignment.hitman.vo.Player;
import org.h2.jdbc.JdbcSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to perform all DB operations related to player. Class has been made singleton.
 *
 * @author ashutoshp
 */
public class PlayerDaoImpl implements PlayerDao {

    private PlayerDaoImpl() {}

    private static class PlayerDaoCreator {
        private static final PlayerDao INSTANCE = new PlayerDaoImpl();
    }

    public static PlayerDao getInstance() {
        return PlayerDaoCreator.INSTANCE;
    }

    @Override
    public Player getPlayerByName(String playerName) {
        Player player = null;
        try (Connection conn = DBConfiguration.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DBConstants.GET_PLAYER_SQL); ) {
            stmt.setString(1, playerName);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                player =
                        new Player(playerName)
                                .setId(resultSet.getObject("id", Integer.class))
                                .setName(resultSet.getObject("name", String.class))
                                .setMoney(resultSet.getObject("money", Integer.class))
                                .setHealth(resultSet.getObject("health", Integer.class))
                                .setLevel(resultSet.getObject("level", Integer.class))
                                .setWeaponId(resultSet.getObject("weapon_id", Integer.class));
                if (resultSet.getObject("opponent_health") != null) {
                    player.setOpponentHealth(resultSet.getObject("opponent_health", Integer.class));
                    player.setOpponentWeaponId(resultSet.getObject("opponent_weapon_id", Integer.class));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public void createNewPlayer(Player player) throws PlayerAlreadyExistException {
        try (Connection conn = DBConfiguration.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DBConstants.PLAYER_INSERT_SQL); ) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getMoney());
            stmt.setInt(3, player.getHealth());
            stmt.setInt(4, player.getLevel());
            stmt.setInt(5, player.getWeaponId());
            int updatedRows = stmt.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException(MessageConstants.PLAYER_NOT_CREATED);
            }
            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    player.setId(resultSet.getInt(1));
                }
            }
        } catch (JdbcSQLException e) {
            if (e.getMessage().contains("PLAYERS(NAME)")) {
                throw new PlayerAlreadyExistException(
                        String.format(MessageConstants.PLAYER_ALREADY_EXISTS, player.getName()), e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateExistingPlayer(Player player) {
        try (Connection conn = DBConfiguration.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DBConstants.PLAYER_UPDATE_SQL); ) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getMoney());
            stmt.setInt(3, player.getHealth());
            stmt.setInt(4, player.getLevel());
            stmt.setInt(5, player.getWeaponId());
            stmt.setInt(6, player.getOpponentHealth() == null ? 0 : player.getOpponentHealth());
            stmt.setInt(7, player.getOpponentWeaponId() == null ? 0 : player.getOpponentWeaponId());
            stmt.setInt(8, player.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getPlayerCount() {
        Integer playerCount = 0;
        try (Connection conn = DBConfiguration.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DBConstants.GET_PLAYER_COUNT); ) {
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                playerCount = resultSet.getInt("playercount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerCount;
    }
}
