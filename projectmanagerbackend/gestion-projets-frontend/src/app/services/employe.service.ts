import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employe } from '../models/employe.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiUrl = 'http://localhost:8080/api/employes';

  constructor(private http: HttpClient) {}

  createEmployee(employee: Partial<Employe>): Observable<Employe> {
    return this.http.post<Employe>(this.apiUrl, employee);
  }


  updateEmployee(employee: Employe): Observable<Employe> {
    return this.http.put<Employe>(`${this.apiUrl}/${employee.id}`, employee);
  }

  getAllEmployees(): Observable<Employe[]> {
    return this.http.get<Employe[]>(this.apiUrl);
  }

  getEmployeeById(id: number): Observable<Employe> {
    return this.http.get<Employe>(`${this.apiUrl}/${id}`);
  }

  getEmployeesByTeam(equipe: string): Observable<Employe[]> {
    return this.http.get<Employe[]>(`${this.apiUrl}/equipe/${equipe}`);
  }

  assignRole(id: number, nouveauRole: string): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/role?nouveauRole=${nouveauRole}`, {});
  }

  // Service : employee.service.ts

 deleteEmployee(id: number): Observable<void> {
  return this.http.delete<void>(`http://localhost:8080/api/employes/${id}`);
}

  
  

}