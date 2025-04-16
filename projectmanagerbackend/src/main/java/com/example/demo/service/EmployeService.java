package com.example.demo.service;

import com.example.demo.entity.Employe;
import java.util.List;

public interface EmployeService {
    Employe créerEmploye(Employe employe);
    List<Employe> obtenirEmployesParÉquipe(String equipe);
    void assignerRôle(Long employeId, String nouveauRole);
    List<Employe> obtenirTousLesEmployes();
    Employe obtenirEmployeParId(Long id);
    void supprimerEmploye(Long id);
    
	Employe modifierEmploye(Long id, Employe employe);

}