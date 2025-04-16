import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tache } from '../models/tache.model';

@Injectable({
  providedIn: 'root'
})
export class TacheService {
  private apiUrl = 'http://localhost:8080/api/taches';

  constructor(private http: HttpClient) { }

  createTask(tache: Tache): Observable<Tache> {
    return this.http.post<Tache>(this.apiUrl, tache);
  }

  getTaskById(id: number): Observable<Tache> {
    return this.http.get<Tache>(`${this.apiUrl}/${id}`);
  }

  getAllTasks(): Observable<Tache[]> {
    return this.http.get<Tache[]>(this.apiUrl);
  }

  updateTask(id: number, tache: Tache): Observable<Tache> {
    return this.http.put<Tache>(`${this.apiUrl}/${id}`, tache);
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getTasksByProject(projetId: number): Observable<Tache[]> {
    return this.http.get<Tache[]>(`${this.apiUrl}/projet/${projetId}`);
  }

  getTasksByResponsable(responsableId: number): Observable<Tache[]> {
    return this.http.get<Tache[]>(`${this.apiUrl}/responsable/${responsableId}`);
  }

  getTasksByEtat(etat: string): Observable<Tache[]> {
    return this.http.get<Tache[]>(`${this.apiUrl}/etat/${etat}`);
  }
}