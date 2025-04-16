package com.example.demo.service.impl;

import com.example.demo.entity.Produit;
import com.example.demo.repository.ProduitRepository;
import com.example.demo.service.ProduitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // Par défaut, lecture seule pour optimisation
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    @Transactional
    public Produit creerProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être null");
        }
        return produitRepository.save(produit);
    }

    @Override
    public Produit obtenirProduitParId(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Produit> obtenirTousLesProduits() {
        return produitRepository.findAll();
    }

    @Override
    @Transactional
    public Produit mettreAJourProduit(Long id, Produit produit) {
        Produit produitExistant = obtenirProduitParId(id);
        produitExistant.setNom(produit.getNom());
        produitExistant.setDescription(produit.getDescription());
        produitExistant.setPrix(produit.getPrix());
        produitExistant.setQuantite(produit.getQuantite());
        return produitRepository.save(produitExistant);
    }

    @Override
    @Transactional
    public void supprimerProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé avec l'ID : " + id);
        }
        produitRepository.deleteById(id);
    }

    @Override
    public List<Produit> rechercherProduitsParNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide ou null");
        }
        return produitRepository.findByNomContainingIgnoreCase(nom);
    }
}