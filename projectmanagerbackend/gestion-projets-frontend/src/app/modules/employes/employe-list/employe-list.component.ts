import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpErrorResponse } from '@angular/common/http';
import { Employe } from '../../../models/employe.model';
import { EmployeeService } from '../../../services/employe.service'; // Ajuste le chemin si nécessaire

@Component({
  selector: 'app-employe-list',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './employe-list.component.html',
  styleUrls: ['./employe-list.component.scss']
})
export class EmployeListComponent implements OnInit {
  employes: Employe[] = [];
  isLoading = true;
  error: string | null = null;
  newEmploye: Partial<Employe> = { nom: '', email: '', role: '', equipe: '' };
  isEditing: boolean = false; // Pour savoir si on est en mode édition
  selectedEmploye: Employe | null = null; // Pour l'employé sélectionné à éditer (utilise maintenant `Employe` au lieu de `Partial<Employe>`)

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.fetchEmployes();
  }

  // Récupérer la liste des employés
  fetchEmployes(): void {
    this.isLoading = true;
    this.error = null;

    this.employeeService.getAllEmployees().subscribe({
      next: (data) => {
        this.employes = data;
        this.isLoading = false;
      },
      error: (err: HttpErrorResponse) => {
        if (err.status === 0 && err.statusText === 'Unknown Error') {
          this.error = 'Impossible de se connecter au serveur. Vérifiez la configuration CORS ou si le serveur est en cours d\'exécution.';
        } else {
          this.error = err.error?.message || 'Une erreur est survenue lors du chargement des employés.';
        }
        console.error('Erreur HTTP:', err);
        this.isLoading = false;
      }
    });
  }

  // Ajouter un nouvel employé
  addEmploye(): void {
    if (!this.newEmploye.nom || !this.newEmploye.email || !this.newEmploye.role || !this.newEmploye.equipe) {
      this.error = 'Veuillez remplir tous les champs obligatoires.';
      return;
    }

    // Créer l'employé
    const employeToCreate: Partial<Employe> = {
      nom: this.newEmploye.nom,
      email: this.newEmploye.email,
      role: this.newEmploye.role,
      equipe: this.newEmploye.equipe
    };

    this.employeeService.createEmployee(employeToCreate).subscribe({
      next: (newEmploye) => {
        this.employes.push(newEmploye); // Ajouter à la liste
        this.newEmploye = { nom: '', email: '', role: '', equipe: '' }; // Réinitialiser le formulaire
        this.error = null;
      },
      error: (err: HttpErrorResponse) => {
        this.error = err.error?.message || 'Une erreur est survenue lors de l\'ajout de l\'employé.';
        console.error('Erreur HTTP:', err);
      }
    });
  }

  // Modifier un employé
  editEmploye(employe: Employe): void {
    this.isEditing = true;
    this.selectedEmploye = { ...employe }; // Copier les données de l'employé à éditer
  }

  // Sauvegarder les modifications d'un employé
  saveEmploye(): void {
    if (!this.selectedEmploye?.nom || !this.selectedEmploye?.email || !this.selectedEmploye?.role || !this.selectedEmploye?.equipe) {
      this.error = 'Veuillez remplir tous les champs obligatoires.';
      return;
    }

    this.employeeService.updateEmployee(this.selectedEmploye).subscribe({
      next: (updatedEmploye) => {
        const index = this.employes.findIndex(emp => emp.id === updatedEmploye.id);
        if (index > -1) {
          this.employes[index] = updatedEmploye; // Mettre à jour l'employé dans la liste
        }
        this.isEditing = false; // Stopper l'édition
        this.selectedEmploye = null; // Réinitialiser l'employé sélectionné
        this.error = null;
      },
      error: (err: HttpErrorResponse) => {
        this.error = err.error?.message || 'Une erreur est survenue lors de la mise à jour de l\'employé.';
        console.error('Erreur HTTP:', err);
      }
    });
  }

  deleteEmployee(id: number): void {
    console.log('ID de l\'employé à supprimer:', id); // Vérification de l'ID
    if (confirm('Êtes-vous sûr de vouloir supprimer cet employé ?')) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: () => {
          console.log(`Employé avec ID ${id} supprimé.`);
          // Retirer l'employé supprimé de la liste
          this.employes = this.employes.filter(emp => emp.id !== id);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression:', err);
          this.error = 'Une erreur est survenue lors de la suppression de l\'employé.';
        }
      });
    }
  }
  


  // Annuler l'édition
  cancelEdit(): void {
    this.isEditing = false;
    this.selectedEmploye = null;
  }
}
