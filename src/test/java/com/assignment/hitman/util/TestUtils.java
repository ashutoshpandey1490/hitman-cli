package com.assignment.hitman.util;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility method for test cases.
 *
 * @author ashutoshp
 */
public class TestUtils {

    private static Server server;

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:tcp://localhost/~/hitmanTest";

    //  Database credentials
    private static final String USER = "sa";
    private static final String PASS = "";

    public static void initDB() throws SQLException {
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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void destroy() {
        server.stop();
    }
}
