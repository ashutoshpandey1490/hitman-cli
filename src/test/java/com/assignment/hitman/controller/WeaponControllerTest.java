package com.assignment.hitman.controller;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.TestUtils;
import com.assignment.hitman.vo.Player;
import com.assignment.hitman.vo.Weapon;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test class for {@link WeaponController}
 *
 * @author ashutoshp
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBConfiguration.class)
public class WeaponControllerTest {

    @BeforeClass
    public static void init() throws SQLException {
        TestUtils.initDB();
    }

    @Test
    public void testGetSystemWeapon() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Player player = new Player("Test");
        Weapon weapon = WeaponController.getInstance().getSystemWeapon(player);
        assertThat("should be of level 1", weapon.getLevel().equals(player.getLevel()));
    }

    @Test
    public void testGetWeaponById() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Weapon weapon = WeaponController.getInstance().getWeaponById(1);
        assertThat("should be of level 1", weapon.getName().equals("Test Knife"));
    }

    @AfterClass
    public static void destroy() {
        TestUtils.destroy();
    }
}
