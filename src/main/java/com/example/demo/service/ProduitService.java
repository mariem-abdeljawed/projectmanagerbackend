package com.example.demo.service;

import com.example.demo.entity.Produit;
import java.util.List;

public interface ProduitService {
    Produit creerProduit(Produit produit);
    Produit obtenirProduitParId(Long id);
    List<Produit> obtenirTousLesProduits();
    Produit mettreAJourProduit(Long id, Produit produit);
    void supprimerProduit(Long id);
    List<Produit> rechercherProduitsParNom(String nom);
}