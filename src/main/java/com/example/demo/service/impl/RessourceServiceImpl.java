package com.example.demo.service.impl;

import com.example.demo.entity.Ressource;
import com.example.demo.repository.RessourceRepository;
import com.example.demo.service.RessourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RessourceServiceImpl implements RessourceService {

    private final RessourceRepository ressourceRepository;

    public RessourceServiceImpl(RessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
    }

    @Override
    @Transactional
    public Ressource creerRessource(Ressource ressource) {
        if (ressource == null) {
            throw new IllegalArgumentException("La ressource ne peut pas être null");
        }
        return ressourceRepository.save(ressource);
    }

    @Override
    public Ressource obtenirRessourceParId(Long id) {
        return ressourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource non trouvée avec l'ID : " + id));
    }

    @Override
    public List<Ressource> obtenirToutesLesRessources() {
        return ressourceRepository.findAll();
    }

    @Override
    @Transactional
    public Ressource mettreAJourRessource(Long id, Ressource ressource) {
        Ressource ressourceExistante = obtenirRessourceParId(id);
        ressourceExistante.setNom(ressource.getNom());
        ressourceExistante.setType(ressource.getType());
        ressourceExistante.setCout(ressource.getCout());
        ressourceExistante.setDisponibilite(ressource.isDisponibilite());
        return ressourceRepository.save(ressourceExistante);
    }

    @Override
    @Transactional
    public void supprimerRessource(Long id) {
        Ressource ressource = obtenirRessourceParId(id);
        if (!ressource.getProjets().isEmpty()) {
            throw new IllegalStateException("Impossible de supprimer la ressource car elle est associée à des projets");
        }
        ressourceRepository.deleteById(id);
    }

    @Override
    public List<Ressource> rechercherRessourcesParType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Le type ne peut pas être vide ou null");
        }
        return ressourceRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public List<Ressource> obtenirRessourcesDisponibles() {
        return ressourceRepository.findByDisponibiliteTrue();
    }

    @Override
    public List<Ressource> obtenirRessourcesParType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Le type ne peut pas être vide ou null");
        }
        return ressourceRepository.findByType(type);
    }

    @Override
    public double calculerCoutTotalRessources() {
        List<Ressource> ressources = ressourceRepository.findAll();
        return ressources.stream()
                .mapToDouble(Ressource::getCout)
                .sum();
    }
}