package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.util.DatabaseConnection;


public class Prueba {
    public static void main(String[] args) {

        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try {
          myConnection = DatabaseConnection.getInstance();
          myStatement = myConnection.createStatement();

          int rowsAffected = myStatement.executeUpdate(
            "Update sakila.actor set first_name = 'Marcos'");
            System.out.println("filas actualizadas:"+rowsAffected);
          myResultSet = myStatement.executeQuery("Select * from sakila.actor");
          while (myResultSet.next()) {
            System.out.println(myResultSet.getString("first_name") 
            +" "+myResultSet.getString("last_name") );
          }
          System.out.println("Conexion exitosa");
        } catch (Exception e) {
           System.out.println("Error Conexion");
        }


    }
}