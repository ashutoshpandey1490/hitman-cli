package com.assignment.hitman.controller;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.util.TestUtils;
import com.assignment.hitman.vo.Player;
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
 * Test class for {@link PlayerController}
 *
 * @author ashutoshp
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBConfiguration.class)
public class PlayerControllerTest {

    @BeforeClass
    public static void init() throws SQLException {
        TestUtils.initDB();
    }

    @Test
    public void testInitializeSystemPlayer() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Player player = new Player("test");
        player = PlayerController.getInstance().initializeSystemPlayer(player);
        assertThat("Weapon should not be null", player.getCurrentWeapon() != null);
        assertThat("Health should be 100", player.getHealth() == 100);
        assertThat("Weapon should be of level 1", player.getCurrentWeapon().getLevel() == 1);
    }

    @Test
    public void testResumeSystemPlayer() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Player player = new Player("test");
        player.setOpponentHealth(50);
        player.setOpponentWeaponId(1);
        player = PlayerController.getInstance().initializeSystemPlayer(player);
        assertThat("Health should be 50", player.getHealth() == 50);
        assertThat("Weapon should be of level 1", player.getCurrentWeapon().getLevel() == 1);
    }

    @Test
    public void testInitializePlayer() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = TestUtils.getConnection();
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Player player = new Player("test");
        player = PlayerController.getInstance().initializePlayer(player);
        assertThat("Weapon should not be null", player.getCurrentWeapon() != null);
        assertThat("WeaponId should be 2", player.getCurrentWeapon().getId() == 2);
    }

    @AfterClass
    public static void destroy() {
        TestUtils.destroy();
    }
}
