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
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
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
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
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

    @Override
    public void save(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("El actor no puede ser nulo");
        }

        // Verificar si el actor ya existe
        Actor existingActor = getByID(actor.getActorID());
        
        if (existingActor != null) {
            // Actualizar actor existente
            updateActor(actor);
        } else {
            // Insertar nuevo actor
            insertActor(actor);
        }
    }

    private void insertActor(Actor actor) {
        String sql = "INSERT INTO actor (actor_id, first_name, last_name) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, actor.getActorID());
            pstmt.setString(2, actor.getFirstName());
            pstmt.setString(3, actor.getLastName());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Fallo al insertar actor, ninguna fila afectada.");
            }
            
            System.out.println("Actor insertado correctamente: " + actor);
        } catch (SQLException e) {
            System.err.println("Error al insertar actor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void updateActor(Actor actor) {
        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, actor.getFirstName());
            pstmt.setString(2, actor.getLastName());
            pstmt.setInt(3, actor.getActorID());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Fallo al actualizar actor, ninguna fila afectada.");
            }
            
            System.out.println("Actor actualizado correctamente: " + actor);
        } catch (SQLException e) {
            System.err.println("Error al actualizar actor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM actor WHERE actor_id = ?";
        
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows == 0) {
                System.out.println("No se encontró actor con ID: " + id);
            } else {
                System.out.println("Actor eliminado correctamente. ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar actor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Actor mapActor(ResultSet rs) throws SQLException {
        return new Actor(
            rs.getInt("actor_id"),
            rs.getString("first_name"),
            rs.getString("last_name")
        );
    }
}