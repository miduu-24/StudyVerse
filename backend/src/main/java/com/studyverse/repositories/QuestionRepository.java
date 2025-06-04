
package com.studyverse.repositories;

import com.studyverse.models.Question;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByIdIn(Set<Long> ids);
}
