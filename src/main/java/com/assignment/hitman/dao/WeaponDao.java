package com.assignment.hitman.dao;

import com.assignment.hitman.vo.Weapon;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ashutoshp
 */
public interface WeaponDao {
    public List<Weapon> getAllWeapons() throws SQLException;
}
