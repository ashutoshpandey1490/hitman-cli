package com.assignment.hitman.dao;

import com.assignment.hitman.vo.Weapon;

import java.sql.SQLException;
import java.util.List;

/**
 * Class to perform all DB operations related to weapons.
 *
 * @author ashutoshp
 */
public interface WeaponDao {
     List<Weapon> getAllWeapons() throws SQLException;

     Weapon getWeaponById(Integer weaponId) throws SQLException;

     List<Weapon> getWeaponByLevel(Integer level) throws SQLException;
}
