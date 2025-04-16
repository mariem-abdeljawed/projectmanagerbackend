import { Tache } from './tache.model';
import { Ressource } from './ressource.model';


export interface Projet {
    id: number;
    nom: string;
    dateDebut: Date;
    dateFin: Date;
    budget: number;
    statut: string;
    taches?: Tache[];
    ressources?: Ressource[];
  }