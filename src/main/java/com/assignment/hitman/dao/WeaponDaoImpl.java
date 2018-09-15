package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.database.DBConstants;
import com.assignment.hitman.util.WeaponType;
import com.assignment.hitman.vo.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform all DB operations related to weapons. Class has been made singleton.
 *
 * @author ashutoshp
 */
public class WeaponDaoImpl implements WeaponDao {

  private WeaponDaoImpl() {}

  private static class WeaponDaoCreator {
    private static final WeaponDao INSTANCE = new WeaponDaoImpl();
  }

  public static WeaponDao getInstance() {
    return WeaponDaoCreator.INSTANCE;
  }

  @Override
  public List<Weapon> getAllWeapons() throws SQLException {
    List<Weapon> allWeapons = new ArrayList<>();
    try (Connection conn = DBConfiguration.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DBConstants.GET_ALL_WEAPON_SQL); ) {
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        Weapon weapon =
            new Weapon()
                .setId(resultSet.getObject("id", Integer.class))
                .setType(WeaponType.getTypeFromValue(resultSet.getObject("type", Integer.class)))
                .setName(resultSet.getObject("name", String.class))
                .setPrice(resultSet.getObject("price", Integer.class))
                .setLevel(resultSet.getObject("level", Integer.class))
                .setHitValue(resultSet.getObject("hit_value", Integer.class));
        allWeapons.add(weapon);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return allWeapons;
  }

  @Override
  public Weapon getWeaponById(Integer weaponId) throws SQLException {
    Weapon weapon = null;
    try (Connection conn = DBConfiguration.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DBConstants.GET_WEAPON_SQL); ) {
      stmt.setInt(1, weaponId);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        weapon =
            new Weapon()
                .setId(resultSet.getObject("id", Integer.class))
                .setType(WeaponType.getTypeFromValue(resultSet.getObject("type", Integer.class)))
                .setName(resultSet.getObject("name", String.class))
                .setPrice(resultSet.getObject("price", Integer.class))
                .setLevel(resultSet.getObject("level", Integer.class))
                .setHitValue(resultSet.getObject("hit_value", Integer.class));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return weapon;
  }

  @Override
  public List<Weapon> getWeaponByLevel(Integer level) throws SQLException {
    List<Weapon> allWeapons = new ArrayList<>();
    try (Connection conn = DBConfiguration.getConnection();
        PreparedStatement stmt = conn.prepareStatement(DBConstants.GET_WEAPON_LEVEL_SQL); ) {
      stmt.setInt(1, level);
      ResultSet resultSet = stmt.executeQuery();
      while (resultSet.next()) {
        Weapon weapon =
            new Weapon()
                .setId(resultSet.getObject("id", Integer.class))
                .setType(WeaponType.getTypeFromValue(resultSet.getObject("type", Integer.class)))
                .setName(resultSet.getObject("name", String.class))
                .setPrice(resultSet.getObject("price", Integer.class))
                .setLevel(resultSet.getObject("level", Integer.class))
                .setHitValue(resultSet.getObject("hit_value", Integer.class));
        allWeapons.add(weapon);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return allWeapons;
  }
}
