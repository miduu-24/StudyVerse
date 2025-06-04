package com.studyverse.controllers;

import com.studyverse.dto.LessonDTO;
import com.studyverse.exceptions.LessonNotFoundException;
import com.studyverse.models.Lesson;
import com.studyverse.models.Quiz;
import com.studyverse.services.LessonService;
import com.studyverse.services.QuizService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private QuizService quizService;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.findAll();
    }

    @PostMapping
    public Lesson createLesson(@RequestBody @Valid LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setContent(lessonDTO.getContent());
        lesson.setDifficultyLevel(lessonDTO.getDifficultyLevel());
        lesson.setImageUrl(lessonDTO.getImageUrl());

        // Dacă ai quiz-uri deja create și vrei să le asociezi
        if (lessonDTO.getQuizIds() != null && !lessonDTO.getQuizIds().isEmpty()) {
            Set<Quiz> quizzes = new HashSet<>(quizService.findAllByIds(lessonDTO.getQuizIds()));
            lesson.setQuizzes(new ArrayList<>(quizzes));
        }

        return lessonService.save(lesson);
    }

    @GetMapping("/{id}")
    public Lesson getLessonById(@PathVariable Long id) {
        return lessonService.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found with id " + id));
    }

    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long id, @RequestBody @Valid LessonDTO lessonDTO) {
        Lesson lesson = lessonService.findById(id)
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found with id " + id));

        lesson.setTitle(lessonDTO.getTitle());
        lesson.setDescription(lessonDTO.getDescription());
        lesson.setContent(lessonDTO.getContent());
        lesson.setDifficultyLevel(lessonDTO.getDifficultyLevel());
        lesson.setImageUrl(lessonDTO.getImageUrl());

        if (lessonDTO.getQuizIds() != null) {
            Set<Quiz> quizzes = new HashSet<>(quizService.findAllByIds(lessonDTO.getQuizIds()));
            lesson.setQuizzes(new ArrayList<>(quizzes));
        }

        return lessonService.save(lesson);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteById(id);
    }
}
