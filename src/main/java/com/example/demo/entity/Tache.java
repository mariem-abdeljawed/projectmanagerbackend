package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "taches")
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projet_id", nullable = false)
    @JsonIgnoreProperties("taches") // Ignore les éventuelles listes dans Projet pour éviter les boucles
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    @JsonIgnoreProperties("taches") // Ignore les éventuelles listes dans Employe
    private Employe responsable;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String etat;

    @Column(nullable = false)
    private String priorite;

    @Column(nullable = false)
    private LocalDate deadline;

    // Getters et Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }

    public Employe getResponsable() { return responsable; }
    public void setResponsable(Employe responsable) { this.responsable = responsable; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getEtat() { return etat; }
    public void setEtat(String etat) { this.etat = etat; }

    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
}
