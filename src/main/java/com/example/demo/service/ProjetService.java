package com.example.demo.service;

import com.example.demo.entity.Projet;
import java.util.List;

public interface ProjetService {
    Projet créerProjet(Projet projet);
    Projet obtenirProjetParId(Long id);
    List<Projet> obtenirTousLesProjets();
    Projet mettreÀJourProjet(Long id, Projet projet);
    void supprimerProjet(Long id);
    List<Projet> rechercherProjetsParStatut(String statut);
    void assignerRessourceAuProjet(Long projetId, Long ressourceId);
    double calculerBudgetTotal(Long projetId);
}