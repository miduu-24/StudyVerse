
package com.studyverse.repositories;

import com.studyverse.models.Molecule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MoleculeRepository extends JpaRepository<Molecule, Long> {
    Optional<Molecule> findBySymbol(String symbol);
}
