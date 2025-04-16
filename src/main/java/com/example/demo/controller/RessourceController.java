package com.example.demo.controller;

import com.example.demo.entity.Ressource;
import com.example.demo.service.RessourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ressources")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RessourceController {

    private final RessourceService ressourceService;

    public RessourceController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    // Créer une ressource
    @PostMapping
    public ResponseEntity<Ressource> creerRessource(@Valid @RequestBody Ressource ressource) {
        Ressource nouvelleRessource = ressourceService.creerRessource(ressource);
        return new ResponseEntity<>(nouvelleRessource, HttpStatus.CREATED);
    }

    // Récupérer une ressource par ID
    @GetMapping("/{id}")
    public ResponseEntity<Ressource> obtenirRessourceParId(@PathVariable Long id) {
        Ressource ressource = ressourceService.obtenirRessourceParId(id);
        return ResponseEntity.ok(ressource);
    }

    // Lister toutes les ressources
    @GetMapping
    public ResponseEntity<List<Ressource>> obtenirToutesLesRessources() {
        List<Ressource> ressources = ressourceService.obtenirToutesLesRessources();
        return ResponseEntity.ok(ressources);
    }

    // Mettre à jour une ressource
    @PutMapping("/{id}")
    public ResponseEntity<Ressource> mettreAJourRessource(
            @PathVariable Long id,
            @Valid @RequestBody Ressource ressource) {
        Ressource ressourceMiseAJour = ressourceService.mettreAJourRessource(id, ressource);
        return ResponseEntity.ok(ressourceMiseAJour);
    }

    // Supprimer une ressource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRessource(@PathVariable Long id) {
        ressourceService.supprimerRessource(id);
        return ResponseEntity.noContent().build();
    }

    // Rechercher des ressources par type (recherche partielle)
    @GetMapping("/recherche/type/{type}")
    public ResponseEntity<List<Ressource>> rechercherRessourcesParType(@PathVariable String type) {
        List<Ressource> ressources = ressourceService.rechercherRessourcesParType(type);
        return ResponseEntity.ok(ressources);
    }

    // Obtenir les ressources disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Ressource>> obtenirRessourcesDisponibles() {
        List<Ressource> ressources = ressourceService.obtenirRessourcesDisponibles();
        return ResponseEntity.ok(ressources);
    }

    // Obtenir les ressources par type (recherche exacte)
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Ressource>> obtenirRessourcesParType(@PathVariable String type) {
        List<Ressource> ressources = ressourceService.obtenirRessourcesParType(type);
        return ResponseEntity.ok(ressources);
    }

    // Calculer le coût total des ressources
    @GetMapping("/cout-total")
    public ResponseEntity<Double> calculerCoutTotalRessources() {
        double coutTotal = ressourceService.calculerCoutTotalRessources();
        return ResponseEntity.ok(coutTotal);
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

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}