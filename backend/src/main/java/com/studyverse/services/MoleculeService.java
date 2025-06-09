package com.studyverse.services;

import com.studyverse.models.Molecule;
import com.studyverse.repositories.MoleculeRepository;
import com.studyverse.dto.MoleculeDTO;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoleculeService {

    private final MoleculeRepository repository;

    public MoleculeService(MoleculeRepository repository) {
        this.repository = repository;
    }

    public List<MoleculeDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<MoleculeDTO> getBySymbol(String symbol) {
        return repository.findBySymbol(symbol)
                .map(this::mapToDto);
    }

    private MoleculeDTO mapToDto(Molecule molecule) {
        return new MoleculeDTO(
                molecule.getName(),
                molecule.getSymbol(),
                molecule.getAtomicNumber(),
                molecule.getAtomicMass(),
                molecule.getGroupNumber(),
                molecule.getPeriod(),
                molecule.getCategory(),
                molecule.getElectronShells(),
                molecule.getProtons(),
                molecule.getNeutrons(),
                molecule.getElectrons());
    }
}
