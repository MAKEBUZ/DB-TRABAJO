package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/sakila"; 
    private static final String user = "root";
    private static final String pass = "Dialeocma*277353";

    private DatabaseConnection() {
        
    } 

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}