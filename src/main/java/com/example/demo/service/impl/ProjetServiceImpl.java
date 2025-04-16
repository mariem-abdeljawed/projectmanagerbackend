package com.example.demo.service.impl;

import com.example.demo.entity.Projet;
import com.example.demo.repository.ProjetRepository;
import com.example.demo.service.ProjetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

    private final ProjetRepository projetRepository;

    public ProjetServiceImpl(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @Override
    public Projet créerProjet(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public Projet obtenirProjetParId(Long id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Projet> obtenirTousLesProjets() {
        return projetRepository.findAll();
    }

    @Override
    public Projet mettreÀJourProjet(Long id, Projet projet) {
        Projet existingProjet = obtenirProjetParId(id);
        existingProjet.setNom(projet.getNom());
        existingProjet.setBudget(projet.getBudget());
        // Mettez à jour les autres champs...
        return projetRepository.save(existingProjet);
    }

    @Override
    public void supprimerProjet(Long id) {
        projetRepository.deleteById(id);
    }

    @Override
    public double calculerBudgetTotal(Long projetId) {
        // Implémentez la logique de calcul ici
        return ((ProjetService) projetRepository).calculerBudgetTotal(projetId);
    }

	@Override
	public void assignerRessourceAuProjet(Long projetId, Long ressourceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Projet> rechercherProjetsParStatut(String statut) {
		// TODO Auto-generated method stub
		return null;
	}
}