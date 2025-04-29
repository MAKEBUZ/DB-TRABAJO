package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Actor;
import com.example.util.DatabaseConnection;

public class ActorRepository implements Repository<Actor> {

    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        
        String sql = "SELECT actor_id, first_name, last_name FROM actor";
        
        try (Connection conn = DatabaseConnection.getInstance();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            
            while (rs.next()) {
                actors.add(mapActor(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los actores: " + e.getMessage());
            e.printStackTrace();
        }
        
        return actors;
    }

    @Override
    public Actor getByID(Integer id) {
        String sql = "SELECT actor_id, first_name, last_name FROM actor WHERE actor_id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement prepareStatement = conn.prepareStatement(sql)) {
            
            prepareStatement.setInt(1, id);
            try (ResultSet rs = prepareStatement.executeQuery()) {
                if (rs.next()) {
                    return mapActor(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener actor por ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    private Actor mapActor(ResultSet rs) throws SQLException {
        return new Actor(
            rs.getInt("actor_id"),
            rs.getString("first_name"),
            rs.getString("last_name")
        );
    }

    @Override
    public void save(Actor t) {
    }

    @Override
    public void delete(Integer id) {
    }
}