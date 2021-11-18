package com.myorg.mvc;

import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connection {

    private static final Connection instance = new Connection();
    private static final String DB_URL = "src/main/resources/Chinook.db";

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public java.sql.Connection createConnectionDb()
            throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

}