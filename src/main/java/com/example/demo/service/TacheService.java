package com.example.demo.service;

import com.example.demo.entity.Tache;
import java.util.List;

public interface TacheService {
    Tache createTache(Tache tache);
    Tache getTacheById(Long id);
    List<Tache> getAllTaches();
    Tache updateTache(Long id, Tache tache);
    void deleteTache(Long id);
    List<Tache> getTachesByProjet(Long projetId);
    List<Tache> getTachesByResponsable(Long responsableId);
    List<Tache> getTachesByEtat(String etat);
}