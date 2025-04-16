package com.example.demo.controller;

import com.example.demo.entity.Tache;
import com.example.demo.service.TacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taches")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TacheController {

    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    // Créer une tâche
    @PostMapping
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        return ResponseEntity.ok(tacheService.createTache(tache));
    }

    // Obtenir une tâche par ID
    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable Long id) {
        return ResponseEntity.ok(tacheService.getTacheById(id));
    }

    // Obtenir toutes les tâches
    @GetMapping
    public ResponseEntity<List<Tache>> getAllTaches() {
        return ResponseEntity.ok(tacheService.getAllTaches());
    }

    // Mettre à jour une tâche
    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        return ResponseEntity.ok(tacheService.updateTache(id, tache));
    }

    // Supprimer une tâche
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }

    // Obtenir les tâches par projet
    @GetMapping("/projet/{projetId}")
    public ResponseEntity<List<Tache>> getTachesByProjet(@PathVariable Long projetId) {
        return ResponseEntity.ok(tacheService.getTachesByProjet(projetId));
    }

    // Obtenir les tâches par responsable
    @GetMapping("/responsable/{responsableId}")
    public ResponseEntity<List<Tache>> getTachesByResponsable(@PathVariable Long responsableId) {
        return ResponseEntity.ok(tacheService.getTachesByResponsable(responsableId));
    }

    // Obtenir les tâches par état
    @GetMapping("/etat/{etat}")
    public ResponseEntity<List<Tache>> getTachesByEtat(@PathVariable String etat) {
        return ResponseEntity.ok(tacheService.getTachesByEtat(etat));
    }
}
