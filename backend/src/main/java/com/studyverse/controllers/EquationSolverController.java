package com.studyverse.controllers;

import com.studyverse.dto.EquationSolverDTO;
import com.studyverse.exceptions.EquationSolverNotFoundException;
import com.studyverse.models.EquationSolver;
import com.studyverse.services.EquationSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/equations")
public class EquationSolverController {

    @Autowired
    private EquationSolverService equationSolverService;

    // Endpoint pentru obținerea tuturor ecuațiilor soluționate
    @GetMapping
    public List<EquationSolver> getAllEquations() {
        return equationSolverService.findAll();
    }

    // Endpoint pentru crearea unei soluții pentru o ecuație
    @PostMapping
    public EquationSolver createEquation(@RequestBody @Valid EquationSolverDTO equationSolverDTO) {
        EquationSolver equationSolver = new EquationSolver();
        equationSolver.setEquation(equationSolverDTO.getEquation());
        equationSolver.setSolution(equationSolverDTO.getSolution());
        equationSolver.setUserId(equationSolverDTO.getUserId());

        return equationSolverService.save(equationSolver);
    }

    // Endpoint pentru obținerea unei soluții pentru ecuația cu ID-ul specificat
    @GetMapping("/{id}")
    public EquationSolver getEquationById(@PathVariable Long id) {
        return equationSolverService.findById(id).orElseThrow(() -> new EquationSolverNotFoundException("Equation not found with id " + id));
    }

    // Endpoint pentru actualizarea soluției unei ecuații
    @PutMapping("/{id}")
    public EquationSolver updateEquation(@PathVariable Long id, @RequestBody @Valid EquationSolverDTO equationSolverDTO) {
        EquationSolver equationSolver = equationSolverService.findById(id).orElseThrow(() -> new EquationSolverNotFoundException("Equation not found with id " + id));
        equationSolver.setEquation(equationSolverDTO.getEquation());
        equationSolver.setSolution(equationSolverDTO.getSolution());
        equationSolver.setUserId(equationSolverDTO.getUserId());

        return equationSolverService.save(equationSolver);
    }

    // Endpoint pentru ștergerea unei ecuații
    @DeleteMapping("/{id}")
    public void deleteEquation(@PathVariable Long id) {
        equationSolverService.deleteById(id);
    }
}
