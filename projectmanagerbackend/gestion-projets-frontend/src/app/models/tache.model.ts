// src/app/models/tache.model.ts
export interface Tache {
    id: number;
    description: string;
    etat: string;
    priorite: string;
    deadline: Date;
    projetId: number;  // Juste l'ID pour éviter les dépendances circulaires
    responsableId: number; // ID de l'employé plutôt que l'objet complet
  }