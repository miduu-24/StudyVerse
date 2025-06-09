package com.studyverse.controllers;

import com.studyverse.dto.MoleculeDTO;
import com.studyverse.exceptions.MoleculeNotFoundException;
import com.studyverse.models.Molecule;
import com.studyverse.services.MoleculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/molecules")
public class MoleculeController {

    private final MoleculeService moleculeService;

    public MoleculeController(MoleculeService moleculeService) {
        this.moleculeService = moleculeService;
    }

    @GetMapping
    public List<MoleculeDTO> getAllMolecules() {
        return moleculeService.getAll();
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<MoleculeDTO> getBySymbol(@PathVariable String symbol) {
        return moleculeService.getBySymbol(symbol.toUpperCase())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
