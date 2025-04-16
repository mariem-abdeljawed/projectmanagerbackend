package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception levée lorsqu'une ressource n'est pas trouvée en base de données.
 * Retourne automatiquement un statut HTTP 404 (NOT FOUND) quand attrapée par le ControllerAdvice.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructeur avec message d'erreur
     * @param message le détail de l'erreur (visible dans la réponse API)
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause racine
     * @param message le détail de l'erreur
     * @param cause l'exception originale (pour le logging)
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructeur avec paramètre pour les requêtes par ID
     * @param resourceName le nom de la ressource (ex: "Tache", "Projet")
     * @param fieldName le nom du champ (ex: "id", "code")
     * @param fieldValue la valeur recherchée
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s non trouvé(e) avec %s = '%s'", 
            resourceName, fieldName, fieldValue));
    }
}