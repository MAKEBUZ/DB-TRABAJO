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
            Actor actor1 = actorRepository.getByID(1);
            System.out.println(actor1 != null ? actor1 : "Actor no encontrado");

            System.out.println("\n=== Actualizando actor con ID 1 ===");
            Actor toUpdate = new Actor(1, "NuevoNombre", "NuevoApellido");
            actorRepository.save(toUpdate);

            System.out.println("\n=== Insertando nuevo actor ===");
            Actor newActor = new Actor(969, "ActorNuevo", "ApellidoNuevo");
            actorRepository.save(newActor);

            System.out.println("\n=== Eliminando actor con ID 999 ===");
            actorRepository.delete(969);

            System.out.println("\n=== Actor con ID 1 después de actualizar ===");
            System.out.println(actorRepository.getByID(1));

            System.out.println("\n=== Actor con ID 999 después de eliminar ===");
            System.out.println(actorRepository.getByID(999) != null ? 
                "Actor encontrado" : "Actor no encontrado (eliminación exitosa)");

        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}