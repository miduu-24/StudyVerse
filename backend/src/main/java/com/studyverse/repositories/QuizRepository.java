
package com.studyverse.repositories;

import com.studyverse.models.Quiz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findAllByIdIn(List<Long> ids);

}
