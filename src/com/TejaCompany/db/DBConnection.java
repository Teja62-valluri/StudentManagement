package com.TejaCompany.db;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DBConnection {

    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_mannagement"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC",
                    "Teja",
                    "teja@205101"
                );
            } catch (Exception e) {
                throw new RuntimeException("Failed to establish DB connection", e);
            }
        }
        return connection;
    }
}
