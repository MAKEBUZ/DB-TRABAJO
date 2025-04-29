package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.util.DatabaseConnection;

public class Select {
    public static void main(String[] args) throws SQLException {

        try(
            Connection myConnection = DatabaseConnection.getInstance();
            Statement myStatement = myConnection.createStatement();
            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM sakila.actor") 
        ) {
            while (myResultSet.next()){
                System.err.println(myResultSet.getString("first_name") + " " + myResultSet.getString("last_name"));
            }
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Error Conexion");
        }
    }
}
