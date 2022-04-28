package com.v.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Подключение к БД
    private static final String url = "jdbc:postgresql://localhost:5432/youtube";
    private static final String userName = "v";
    private static final String password = "qwerty";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, userName, password);
    }
}