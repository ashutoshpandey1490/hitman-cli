package com.assignment.hitman.dao;

import com.assignment.hitman.vo.Weapon;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ashutoshp
 */
public interface WeaponDao {
     List<Weapon> getAllWeapons() throws SQLException;
     Weapon getWeaponById(Integer weaponId) throws SQLException;
     List<Weapon> getWeaponByLevel(Integer level) throws SQLException;
}
