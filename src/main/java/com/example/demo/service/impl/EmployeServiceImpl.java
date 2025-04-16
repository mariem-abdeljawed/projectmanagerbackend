package com.example.demo.service.impl;

import com.example.demo.entity.Employe;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) 
public class EmployeServiceImpl implements EmployeService {

    private final EmployeeRepository employeRepository;

    public EmployeServiceImpl(EmployeeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    @Transactional
    public Employe créerEmploye(Employe employe) {
        if (employe == null) {
            throw new IllegalArgumentException("L'employé ne peut pas être null");
        }
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> obtenirEmployesParÉquipe(String equipe) {
        if (equipe == null || equipe.trim().isEmpty()) {
            throw new IllegalArgumentException("L'équipe ne peut pas être vide ou null");
        }
        return employeRepository.findByEquipe(equipe);
    }

    @Override
    @Transactional
    public void assignerRôle(Long employeId, String nouveauRole) {
        if (nouveauRole == null || nouveauRole.trim().isEmpty()) {
            throw new IllegalArgumentException("Le rôle ne peut pas être vide ou null");
        }
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec l'ID : " + employeId));
        employe.setRole(nouveauRole);
        employeRepository.save(employe);
    }

    @Override
    public List<Employe> obtenirTousLesEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe obtenirEmployeParId(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec l'ID : " + id));
    }
    
    @Override
    @Transactional  // Ajout de l'annotation @Transactional ici
    public Employe modifierEmploye(Long id, Employe employe) {
        // Vérifier si l'employé existe
        Optional<Employe> employeExist = employeRepository.findById(id);
        if (employeExist.isPresent()) {
            Employe employeToUpdate = employeExist.get();
            // Mettre à jour les informations de l'employé
            employeToUpdate.setNom(employe.getNom());
            employeToUpdate.setEmail(employe.getEmail());
            employeToUpdate.setRole(employe.getRole());
            employeToUpdate.setEquipe(employe.getEquipe());
            // Sauvegarder l'employé mis à jour dans la base de données
            return employeRepository.save(employeToUpdate);
        }
        throw new RuntimeException("Employé introuvable");
    }

    @Override
    @Transactional
    public void supprimerEmploye(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new RuntimeException("Employé non trouvé avec l'ID : " + id);
        }
        employeRepository.deleteById(id);
    }
}
