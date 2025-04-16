import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ressource } from '../models/ressource.model';

@Injectable({
  providedIn: 'root'
})
export class RessourceService {
  private apiUrl = 'http://localhost:8080/api/ressources';

  constructor(private http: HttpClient) { }

  createResource(ressource: Ressource): Observable<Ressource> {
    return this.http.post<Ressource>(this.apiUrl, ressource);
  }

  getResourceById(id: number): Observable<Ressource> {
    return this.http.get<Ressource>(`${this.apiUrl}/${id}`);
  }

  getAllResources(): Observable<Ressource[]> {
    return this.http.get<Ressource[]>(this.apiUrl);
  }

  updateResource(id: number, ressource: Ressource): Observable<Ressource> {
    return this.http.put<Ressource>(`${this.apiUrl}/${id}`, ressource);
  }

  deleteResource(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchResourcesByType(type: string): Observable<Ressource[]> {
    return this.http.get<Ressource[]>(`${this.apiUrl}/recherche/type/${type}`);
  }

  getAvailableResources(): Observable<Ressource[]> {
    return this.http.get<Ressource[]>(`${this.apiUrl}/disponibles`);
  }

  getResourcesByType(type: string): Observable<Ressource[]> {
    return this.http.get<Ressource[]>(`${this.apiUrl}/type/${type}`);
  }

  calculateTotalResourceCost(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/cout-total`);
  }
}