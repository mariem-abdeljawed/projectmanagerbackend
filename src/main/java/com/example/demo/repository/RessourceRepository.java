package com.example.demo.repository;

import com.example.demo.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
	List<Ressource> findByType(String type); // Recherche exacte par type
    List<Ressource> findByTypeContainingIgnoreCase(String type); // Recherche partielle insensible à la casse
    List<Ressource> findByDisponibiliteTrue(); // Ressources disponibles
}