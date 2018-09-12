package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.WeaponType;
import com.assignment.hitman.vo.Weapon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ashutoshp
 */
public class WeaponDaoImpl implements WeaponDao {

    private static final String allWeaponsSql = "select * from WEAPONS";

    @Override
    public List<Weapon> getAllWeapons() throws SQLException {
        List<Weapon> allWeapons = new ArrayList<>();
        Connection conn = DBConfiguration.getConnection();
        try {
            ResultSet resultSet = conn.createStatement().executeQuery(allWeaponsSql);
            while(resultSet.next()) {
                Weapon weapon = new Weapon()
                        .setId(resultSet.getObject("id",Integer.class))
                        .setType(WeaponType.getTypeFromValue(resultSet.getObject("type",Integer.class)))
                        .setName(resultSet.getObject("name",String.class))
                        .setPrice(resultSet.getObject("price",Integer.class))
                        .setLevel(resultSet.getObject("level",Integer.class))
                        .setHitValue(resultSet.getObject("hit_value",Integer.class));
                allWeapons.add(weapon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            conn.close();
        }
        return allWeapons;
    }
}
