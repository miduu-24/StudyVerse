package com.studyverse.controllers;

import com.studyverse.dto.MoleculeDTO;
import com.studyverse.exceptions.MoleculeNotFoundException;
import com.studyverse.models.Molecule;
import com.studyverse.services.MoleculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/molecules")
public class MoleculeController {

    @Autowired
    private MoleculeService moleculeService;

    // Endpoint pentru obținerea tuturor moleculelor
    @GetMapping
    public List<Molecule> getAllMolecules() {
        return moleculeService.findAll();
    }

    // Endpoint pentru crearea unei molecule
    @PostMapping
    public Molecule createMolecule(@RequestBody @Valid MoleculeDTO moleculeDTO) {
        Molecule molecule = new Molecule();
        molecule.setName(moleculeDTO.getName());
        molecule.setFormula(moleculeDTO.getFormula());
        molecule.setModel3DPath(moleculeDTO.getModel3DPath());

        return moleculeService.save(molecule);
    }

    // Endpoint pentru obținerea unei molecule după ID
    @GetMapping("/{id}")
    public Molecule getMoleculeById(@PathVariable Long id) {
        return moleculeService.findById(id).orElseThrow(() -> new MoleculeNotFoundException("Molecule not found with id " + id));
    }

    // Endpoint pentru actualizarea unei molecule
    @PutMapping("/{id}")
    public Molecule updateMolecule(@PathVariable Long id, @RequestBody @Valid MoleculeDTO moleculeDTO) {
        Molecule molecule = moleculeService.findById(id).orElseThrow(() -> new MoleculeNotFoundException("Molecule not found with id " + id));
        molecule.setName(moleculeDTO.getName());
        molecule.setFormula(moleculeDTO.getFormula());
        molecule.setModel3DPath(moleculeDTO.getModel3DPath());

        return moleculeService.save(molecule);
    }

    // Endpoint pentru ștergerea unei molecule
    @DeleteMapping("/{id}")
    public void deleteMolecule(@PathVariable Long id) {
        moleculeService.deleteById(id);
    }
}
