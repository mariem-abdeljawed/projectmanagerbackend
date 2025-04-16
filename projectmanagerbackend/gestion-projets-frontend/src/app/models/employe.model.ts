// src/app/models/employe.model.ts
import { Tache } from './tache.model';

export interface Employe {
  id: number;
  nom: string;
  email: string;
  role: string;
  equipe: string;
  taches?: Tache[]; // Maintenant reconnu
}