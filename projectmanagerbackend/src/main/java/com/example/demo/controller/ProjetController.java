package com.example.demo.controller;

import com.example.demo.entity.Projet;
import com.example.demo.service.ProjetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projets")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping
    public ResponseEntity<Projet> creerProjet(@Valid @RequestBody Projet projet) {
        Projet nouveauProjet = projetService.créerProjet(projet);
        return new ResponseEntity<>(nouveauProjet, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> obtenirProjet(@PathVariable Long id) {
        Projet projet = projetService.obtenirProjetParId(id);
        return ResponseEntity.ok(projet);
    }

    @GetMapping
    public ResponseEntity<List<Projet>> obtenirTousLesProjets() {
        List<Projet> projets = projetService.obtenirTousLesProjets();
        return ResponseEntity.ok(projets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projet> mettreAJourProjet(
            @PathVariable Long id,
            @Valid @RequestBody Projet projet) {
        Projet projetMaj = projetService.mettreÀJourProjet(id, projet);
        return ResponseEntity.ok(projetMaj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerProjet(@PathVariable Long id) {
        projetService.supprimerProjet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Projet>> rechercherParStatut(@PathVariable String statut) {
        List<Projet> projets = projetService.rechercherProjetsParStatut(statut);
        return ResponseEntity.ok(projets);
    }

    @PostMapping("/{projetId}/ressources/{ressourceId}")
    public ResponseEntity<Void> assignerRessource(
            @PathVariable Long projetId,
            @PathVariable Long ressourceId) {
        projetService.assignerRessourceAuProjet(projetId, ressourceId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/budget")
    public ResponseEntity<Double> calculerBudget(@PathVariable Long id) {
        double budget = projetService.calculerBudgetTotal(id);
        return ResponseEntity.ok(budget);
    }

    // Gestion des exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}