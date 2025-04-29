package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.util.DatabaseConnection;

public class Insert {
     public static void main(String[] args) {

        Connection myConnection = null;
        PreparedStatement myStatement = null;
        ResultSet myResultSet = null;
        try {
          myConnection = DatabaseConnection.getInstance();
          String sql = "Insert into sakila.actor(actor_id,first_name,last_name) values (?,?,?)";
          myStatement = myConnection.prepareStatement(sql);
          myStatement.setInt(1, 981);
          myStatement.setString(2, "Pepito");
          myStatement.setString(3, "Perez22-");

          int rowsAffected = myStatement.executeUpdate();
          System.out.println("filas insertadas: "+rowsAffected);

          System.out.println("Conexion exitosa");
        } catch (Exception e) {
           System.out.println("Error Conexion");
        }


    }
}
