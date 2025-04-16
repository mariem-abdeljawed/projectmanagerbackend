package com.example.demo.controller;

import com.example.demo.entity.Employe;
import com.example.demo.service.EmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping
    public ResponseEntity<Employe> creerEmploye(@RequestBody Employe employe) {
        Employe nouvelEmploye = employeService.créerEmploye(employe);
        return new ResponseEntity<>(nouvelEmploye, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employe>> obtenirTousLesEmployes() {
        List<Employe> employes = employeService.obtenirTousLesEmployes();
        return ResponseEntity.ok(employes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> obtenirEmployeParId(@PathVariable Long id) {
        Employe employe = employeService.obtenirEmployeParId(id);
        return ResponseEntity.ok(employe);
    }

    @GetMapping("/equipe/{equipe}")
    public ResponseEntity<List<Employe>> obtenirEmployesParEquipe(@PathVariable String equipe) {
        List<Employe> employes = employeService.obtenirEmployesParÉquipe(equipe);
        return ResponseEntity.ok(employes);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<Void> assignerRole(
            @PathVariable Long id,
            @RequestParam String nouveauRole) {
        employeService.assignerRôle(id, nouveauRole);
        return ResponseEntity.ok().build();
    }

    
 // Modifier un employé
    @PutMapping("/{id}")
    public ResponseEntity<Employe> modifierEmploye(
            @PathVariable Long id,
            @RequestBody Employe employe) {
        Employe employeModifie = employeService.modifierEmploye(id, employe);
        if (employeModifie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeModifie);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEmploye(@PathVariable Long id) {
        employeService.supprimerEmploye(id);
        return ResponseEntity.noContent().build();
    }
}