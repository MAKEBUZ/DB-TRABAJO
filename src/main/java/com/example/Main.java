package com.example;

import com.example.model.Actor;
import com.example.repository.ActorRepository;

public class Main {
    public static void main(String[] args) {
        ActorRepository actorRepository = new ActorRepository();

        try {
            System.out.println("=== Todos los actores ===");
            actorRepository.findAll().forEach(System.out::println);

            System.out.println("\n=== Actor con ID 1 ===");
            Actor actor = actorRepository.getByID(1);
            System.out.println(actor != null ? actor : "Actor no encontrado");
            
            System.out.println("\n=== Actor con ID 980 ===");
            Actor actor999 = actorRepository.getByID(980);
            System.out.println(actor999 != null ? actor999 : "Actor no encontrado");
            
        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}