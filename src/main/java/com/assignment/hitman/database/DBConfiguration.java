package com.assignment.hitman.database;

import com.assignment.hitman.util.MessageConstants;
import org.h2.tools.Server;

import java.sql.*;

/**
 * Class to deal with DB operations.
 *
 * @author ashutoshp
 */
public class DBConfiguration {

    private static Server server;

    public static void initializeDB() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(MessageConstants.JDBC_DRIVER);
            server = Server.createTcpServer().start();
            conn =
                    DriverManager.getConnection(
                            MessageConstants.DB_URL, MessageConstants.USER, MessageConstants.PASS);
            stmt = conn.createStatement();
            ResultSet rset = conn.getMetaData().getTables(null, null, "WEAPONS", null);
            if (!rset.next()) {
                // Table is already there. Do not initialize it again.
                stmt.execute("runscript from 'classpath:init.sql'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            conn.commit();
            stmt.close();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection(
                            MessageConstants.DB_URL, MessageConstants.USER, MessageConstants.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void stopDB() {
        server.stop();
        server = null;
    }
}
