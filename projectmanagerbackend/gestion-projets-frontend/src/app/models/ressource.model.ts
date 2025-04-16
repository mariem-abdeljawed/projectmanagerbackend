import { Projet } from './projet.model';


export interface Ressource {
    id: number;
    nom: string;
    type: string;
    cout: number;
    disponibilite: boolean;
    projets?: Projet[]; // Relation ManyToMany avec Projet
  }