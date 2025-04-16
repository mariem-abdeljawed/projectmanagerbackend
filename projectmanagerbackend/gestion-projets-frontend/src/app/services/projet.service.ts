import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projet } from '../models/projet.model';

@Injectable({
  providedIn: 'root'
})
export class ProjetService {
  private apiUrl = 'http://localhost:8080/api/projets';

  constructor(private http: HttpClient) { }

  createProject(projet: Projet): Observable<Projet> {
    return this.http.post<Projet>(this.apiUrl, projet);
  }

  getProjectById(id: number): Observable<Projet> {
    return this.http.get<Projet>(`${this.apiUrl}/${id}`);
  }

  getAllProjects(): Observable<Projet[]> {
    return this.http.get<Projet[]>(this.apiUrl);
  }

  updateProject(id: number, projet: Projet): Observable<Projet> {
    return this.http.put<Projet>(`${this.apiUrl}/${id}`, projet);
  }

  deleteProject(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchProjectsByStatus(statut: string): Observable<Projet[]> {
    return this.http.get<Projet[]>(`${this.apiUrl}/statut/${statut}`);
  }

  assignResourceToProject(projetId: number, ressourceId: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${projetId}/ressources/${ressourceId}`, {});
  }

  calculateTotalBudget(projetId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${projetId}/budget`);
  }
}