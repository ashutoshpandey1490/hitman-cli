package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.TestUtils;
import com.assignment.hitman.vo.Weapon;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test class to test all weapon related operations.
 *
 * @author ashutoshp
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBConfiguration.class)
public class WeaponDaoTest {

    @BeforeClass
    public static void init() throws SQLException {
        TestUtils.initDB();
    }

    @Test
    public void testGetAllWeaponTest() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        assertThat("Should be equal to 3", WeaponDaoImpl.getInstance().getAllWeapons().size() == 3);
    }

    @Test
    public void testGetWeaponById() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Weapon weapon = WeaponDaoImpl.getInstance().getWeaponById(3);
        assertThat("Name should be Test Machine gun", weapon.getName().equals("Test Machine gun"));
        assertThat("Price should be 300", weapon.getPrice() == 300);
    }

    @Test
    public void testGetWeaponByLevel() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        List<Weapon> weaponList = WeaponDaoImpl.getInstance().getWeaponByLevel(1);
        assertThat(weaponList, Matchers.hasSize(2));
        assertThat(weaponList, everyItem(hasProperty("level", equalTo(1))));
        assertThat(
                weaponList,
                containsInAnyOrder(
                        hasProperty("name", equalTo("Test Knife")), hasProperty("name", equalTo("Test Gun"))));
    }

    @AfterClass
    public static void destroy() {
        TestUtils.destroy();
    }
}
