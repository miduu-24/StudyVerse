
package com.studyverse.repositories;

import com.studyverse.models.Lesson;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllBySubject(String subject);

}
