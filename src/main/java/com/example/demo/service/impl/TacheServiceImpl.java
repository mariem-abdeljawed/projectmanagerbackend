package com.example.demo.service.impl;

import com.example.demo.entity.Tache;
import com.example.demo.repository.TacheRepository;
import com.example.demo.service.TacheService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TacheServiceImpl implements TacheService {

    private final TacheRepository tacheRepository;

    public TacheServiceImpl(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    @Override
    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public Tache getTacheById(Long id) {
        return tacheRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée avec l'id: " + id));
    }

    @Override
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache updateTache(Long id, Tache tacheDetails) {
        Tache tache = getTacheById(id);
        tache.setDescription(tacheDetails.getDescription());
        tache.setEtat(tacheDetails.getEtat());
        tache.setPriorite(tacheDetails.getPriorite());
        tache.setDeadline(tacheDetails.getDeadline());
        if (tacheDetails.getProjet() != null) {
            tache.setProjet(tacheDetails.getProjet());
        }
        if (tacheDetails.getResponsable() != null) {
            tache.setResponsable(tacheDetails.getResponsable());
        }
        return tacheRepository.save(tache);
    }

    @Override
    public void deleteTache(Long id) {
        Tache tache = getTacheById(id);
        tacheRepository.delete(tache);
    }

    @Override
    public List<Tache> getTachesByProjet(Long projetId) {
        return tacheRepository.findByProjetId(projetId);
    }

    @Override
    public List<Tache> getTachesByResponsable(Long responsableId) {
        return tacheRepository.findByResponsableId(responsableId);
    }

    @Override
    public List<Tache> getTachesByEtat(String etat) {
        return tacheRepository.findByEtat(etat);
    }
}