
package com.studyverse.repositories;

import com.studyverse.models.Molecule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoleculeRepository extends JpaRepository<Molecule, Long> {
}

