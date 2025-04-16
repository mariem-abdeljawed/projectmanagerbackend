package com.example.demo.repository;

import com.example.demo.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Long> {
    List<Tache> findByProjetId(Long projetId);
    List<Tache> findByResponsableId(Long responsableId);
    List<Tache> findByEtat(String etat);
}