package com.studyverse.services;

import com.studyverse.models.EquationSolver;
import com.studyverse.repositories.EquationSolverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquationSolverService {
    @Autowired
    private EquationSolverRepository equationSolverRepository;

    public List<EquationSolver> findAll() {
        return equationSolverRepository.findAll();
    }

    public Optional<EquationSolver> findById(Long id) {
        return equationSolverRepository.findById(id);
    }

    public EquationSolver save(EquationSolver equationSolver) {
        return equationSolverRepository.save(equationSolver);
    }

    public void deleteById(Long id) {
        equationSolverRepository.deleteById(id);
    }
    
}
