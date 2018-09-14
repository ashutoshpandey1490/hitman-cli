package com.assignment.hitman.database;

import java.sql.*;

/** @author ashutoshp */
public class DBConfiguration {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/hitmanDS";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    public static void initializeDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rset = conn.getMetaData().getTables(null, null, "WEAPONS", null);
            if (!rset.next()) {
                // Table is already there. Do not initialize it again.
                stmt.execute("runscript from 'classpath:init.sql'");
            }
            conn.commit();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
