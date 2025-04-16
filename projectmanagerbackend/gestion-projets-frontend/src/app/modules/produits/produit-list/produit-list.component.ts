import { Component, OnInit } from '@angular/core';
import { ProduitService, Produit } from '../../../services/produit.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-produit-list',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './produit-list.component.html',
  styleUrl: './produit-list.component.scss'
})
export class ProduitListComponent implements OnInit {

  produits: Produit[] = [];

  // ✅ Champs du formulaire d'ajout
  produitForm: Produit = {
    nom: '',
    prix: 0,
    description: '',
   
  };

  // ✅ Champs de recherche
  produitRecherche: string = '';

  // ✅ Produit en cours de modification
  produitEnEdition: Produit | null = null;

  constructor(private produitService: ProduitService) {}

  ngOnInit(): void {
    this.getProduits();
  }

  getProduits(): void {
    this.produitService.getProduits().subscribe(data => {
      this.produits = data;
    });
  }

  ajouterProduit(): void {
    this.produitService.creerProduit(this.produitForm).subscribe(() => {
      this.getProduits();
      this.produitForm = {
        nom: '',
        prix: 0,
        description: '',
        
      };
    });
  }

  modifierProduit(produit: Produit): void {
    this.produitEnEdition = { ...produit }; // Clone pour éviter modification directe
  }

  enregistrerModification(): void {
    if (this.produitEnEdition && this.produitEnEdition.id !== undefined) {
      this.produitService.mettreAJourProduit(this.produitEnEdition.id, this.produitEnEdition).subscribe(() => {
        this.getProduits();
        this.produitEnEdition = null;
      });
    }
  }

  annulerModification(): void {
    this.produitEnEdition = null;
  }

  supprimerProduit(id: number): void {
    this.produitService.supprimerProduit(id).subscribe(() => {
      this.getProduits();
    });
  }

  rechercherProduits(): void {
    if (this.produitRecherche.trim() !== '') {
      this.produitService.rechercherParNom(this.produitRecherche).subscribe(data => {
        this.produits = data;
      });
    }
  }

  resetRecherche(): void {
    this.produitRecherche = '';
    this.getProduits();
  }
}
