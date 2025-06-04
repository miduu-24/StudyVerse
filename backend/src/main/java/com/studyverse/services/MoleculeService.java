package com.studyverse.services;

import com.studyverse.models.Molecule;
import com.studyverse.repositories.MoleculeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoleculeService {
    @Autowired
    private MoleculeRepository moleculeRepository;

    public List<Molecule> findAll() {
        return moleculeRepository.findAll();
    }

    public Optional<Molecule> findById(Long id) {
        return moleculeRepository.findById(id);
    }

    public Molecule save(Molecule molecule) {
        return moleculeRepository.save(molecule);
    }

    public void deleteById(Long id) {
        moleculeRepository.deleteById(id);
    }
    
}
