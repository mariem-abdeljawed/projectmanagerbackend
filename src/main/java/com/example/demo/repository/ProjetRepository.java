package com.example.demo.repository;

import com.example.demo.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    List<Projet> findByStatut(String statut);
}