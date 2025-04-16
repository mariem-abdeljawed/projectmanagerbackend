package com.example.demo.service;

import com.example.demo.entity.Ressource;
import java.util.List;

public interface RessourceService {
    Ressource creerRessource(Ressource ressource);
    Ressource obtenirRessourceParId(Long id);
    List<Ressource> obtenirToutesLesRessources();
    Ressource mettreAJourRessource(Long id, Ressource ressource);
    void supprimerRessource(Long id);
    List<Ressource> rechercherRessourcesParType(String type);
    List<Ressource> obtenirRessourcesDisponibles();
    List<Ressource> obtenirRessourcesParType(String type);
    double calculerCoutTotalRessources();
}