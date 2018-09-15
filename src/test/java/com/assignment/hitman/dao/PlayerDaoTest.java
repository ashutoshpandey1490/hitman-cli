package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.exception.PlayerAlreadyExistException;
import com.assignment.hitman.vo.Player;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test class to test player related DB operations.
 *
 * @author ashutoshp
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBConfiguration.class)
public class PlayerDaoTest {

    private static Server server;

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/hitmanTest";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    @BeforeClass
    public static void init() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            server = Server.createTcpServer().start();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            stmt.execute("runscript from 'classpath:init.sql'");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            conn.commit();
            stmt.close();
        }
    }

    @Test
    public void testCreateNewPlayer() throws PlayerAlreadyExistException, SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        PlayerDao playerDao = PlayerDaoImpl.getInstance();
        playerDao.createNewPlayer(new Player("Test"));
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        // Test may run in any order so value can more than 1. Hence checking > 0
        assertThat("Should be more than 0", playerDao.getPlayerCount() > 0);
    }

    @Test(expected = PlayerAlreadyExistException.class)
    public void testCreateNewPlayerWithExistingData()
            throws PlayerAlreadyExistException, SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        PlayerDao playerDao = PlayerDaoImpl.getInstance();
        playerDao.createNewPlayer(new Player("TestPlayer"));
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        playerDao.createNewPlayer(new Player("TestPlayer"));
    }

    @Test
    public void testPlayerUpdate() throws PlayerAlreadyExistException, SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Player player = new Player("NewPlayer");
        PlayerDaoImpl.getInstance().createNewPlayer(player);
        player.setHealth(50).setMoney(70).setWeaponId(3);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        PlayerDaoImpl.getInstance().updateExistingPlayer(player);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Player result = PlayerDaoImpl.getInstance().getPlayerByName(player.getName());
        assertEquals(50, result.getHealth().intValue());
        assertEquals(70, result.getMoney().intValue());
        assertEquals(3, result.getWeaponId().intValue());
    }

    @AfterClass
    public static void destroy() {
        server.stop();
    }
}
