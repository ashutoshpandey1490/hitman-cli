package com.assignment.hitman.dao;

import com.assignment.hitman.database.DBConfiguration;
import com.assignment.hitman.vo.Weapon;
import org.h2.tools.Server;
import org.hamcrest.Matchers;
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
    public void testGetAllWeaponTest() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        assertThat("Should be equal to 3", WeaponDaoImpl.getInstance().getAllWeapons().size() == 3);
    }

    @Test
    public void testGetWeaponById() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        Weapon weapon = WeaponDaoImpl.getInstance().getWeaponById(3);
        assertThat("Name should be Test Machine gun", weapon.getName().equals("Test Machine gun"));
        assertThat("Price should be 300", weapon.getPrice() == 300);
    }

    @Test
    public void testGetWeaponByLevel() throws SQLException {
        PowerMockito.mockStatic(DBConfiguration.class);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        when(DBConfiguration.getConnection()).thenReturn(conn);
        List<Weapon> weaponList = WeaponDaoImpl.getInstance().getWeaponByLevel(1);
        assertThat(weaponList, Matchers.hasSize(2));
        assertThat(weaponList, everyItem(hasProperty("level", equalTo(1))));
        assertThat(
                weaponList,
                containsInAnyOrder(
                        hasProperty("name", equalTo("Test Knife")), hasProperty("name", equalTo("Test Gun"))));
    }
}
