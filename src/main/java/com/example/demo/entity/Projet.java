package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projets")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du projet est obligatoire")
    @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String nom;

    @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Future(message = "La date de fin doit être dans le futur")
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @PositiveOrZero(message = "Le budget ne peut pas être négatif")
    @Column(nullable = false)
    private double budget;

    @NotBlank(message = "Le statut est obligatoire")
    @Column(nullable = false, length = 20)
    private String statut;

    @OneToMany(mappedBy = "projet", 
               cascade = CascadeType.ALL, 
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Tache> taches = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "projet_ressources",
        joinColumns = @JoinColumn(name = "projet_id"),
        inverseJoinColumns = @JoinColumn(name = "ressource_id")
    )
    private List<Ressource> ressources = new ArrayList<>();

    // Méthodes utilitaires
    public void addTache(Tache tache) {
        taches.add(tache);
        tache.setProjet(this);
    }

    public void removeTache(Tache tache) {
        taches.remove(tache);
        tache.setProjet(null);
    }

    public void addRessource(Ressource ressource) {
        ressources.add(ressource);
        ressource.getProjets().add(this);
    }

    public void removeRessource(Ressource ressource) {
        ressources.remove(ressource);
        ressource.getProjets().remove(this);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        // Validation cohérence des dates
        if (dateFin != null && dateDebut != null && dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }
        this.dateFin = dateFin;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public List<Ressource> getRessources() {
        return ressources;
    }

    public void setRessources(List<Ressource> ressources) {
        this.ressources = ressources;
    }
}