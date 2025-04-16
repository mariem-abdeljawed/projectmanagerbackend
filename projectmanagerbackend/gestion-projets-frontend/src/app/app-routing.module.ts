import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Importations uniquement pour les composants de liste
import { EmployeListComponent } from './modules/employes/employe-list/employe-list.component';
import { ProjetListComponent } from './modules/projets/projet-list/projet-list.component';
import { TacheListComponent } from './modules/taches/tache-list/tache-list.component';
import { RessourceListComponent } from './modules/ressources/ressource-list/ressource-list.component';
import { ProduitListComponent } from './modules/produits/produit-list/produit-list.component';

export const routes: Routes = [
  { path: 'employes', component: EmployeListComponent },
  { path: 'projets', component: ProjetListComponent },
  { path: 'taches', component: TacheListComponent },
  { path: 'ressources', component: RessourceListComponent },
  { path: 'produits', component: ProduitListComponent },
  { path: '', redirectTo: '/projets', pathMatch: 'full' },
  { path: '**', redirectTo: '/projets' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }