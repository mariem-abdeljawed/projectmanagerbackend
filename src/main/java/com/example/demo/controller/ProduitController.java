package com.example.demo.controller;

import com.example.demo.entity.Produit;
import com.example.demo.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public ResponseEntity<Produit> creerProduit(@Valid @RequestBody Produit produit) {
        Produit nouveauProduit = produitService.creerProduit(produit);
        return new ResponseEntity<>(nouveauProduit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> obtenirProduitParId(@PathVariable Long id) {
        Produit produit = produitService.obtenirProduitParId(id);
        return ResponseEntity.ok(produit);
    }

    @GetMapping
    public ResponseEntity<List<Produit>> obtenirTousLesProduits() {
        List<Produit> produits = produitService.obtenirTousLesProduits();
        return ResponseEntity.ok(produits);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> mettreAJourProduit(
            @PathVariable Long id,
            @Valid @RequestBody Produit produit) {
        // Logique de mise à jour complète d'un produit
        Produit produitMisAJour = produitService.mettreAJourProduit(id, produit);
        return ResponseEntity.ok(produitMisAJour);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProduit(@PathVariable Long id) {
        produitService.supprimerProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Produit>> rechercherProduitsParNom(@RequestParam String nom) {
        List<Produit> produits = produitService.rechercherProduitsParNom(nom);
        return ResponseEntity.ok(produits);
    }

    // Gestion des exceptions locales
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
