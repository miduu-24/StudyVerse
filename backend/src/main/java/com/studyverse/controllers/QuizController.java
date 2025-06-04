package com.studyverse.controllers;

import com.studyverse.dto.QuizDTO;
import com.studyverse.exceptions.QuizNotFoundException;
import com.studyverse.exceptions.LessonNotFoundException;
import com.studyverse.models.Quiz;
import com.studyverse.models.Question;
import com.studyverse.models.Lesson;
import com.studyverse.services.QuizService;
import com.studyverse.services.QuestionService;
import com.studyverse.services.LessonService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.findAll();
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody @Valid QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizDTO.getQuizName());
        quiz.setQuizDescription(quizDTO.getQuizDescription());
        quiz.setQuizDuration(quizDTO.getQuizDuration());
        quiz.setTotalQuestions(quizDTO.getTotalQuestions());
        quiz.setTotalMarks(quizDTO.getTotalMarks());
        quiz.setPassingMarks(quizDTO.getPassingMarks());
        quiz.setQuizStatus(quizDTO.getQuizStatus());

        // Setăm întrebările
        Set<Question> questions = new HashSet<>(questionService.findAllByIds(quizDTO.getQuestionIds()));
        quiz.setQuestions(questions);

        // Setăm lecția asociată
        Lesson lesson = lessonService.findById(quizDTO.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found with id " + quizDTO.getLessonId()));
        quiz.setLesson(lesson);

        return quizService.save(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id, @RequestBody @Valid QuizDTO quizDTO) {
        Quiz quiz = quizService.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));

        quiz.setQuizName(quizDTO.getQuizName());
        quiz.setQuizDescription(quizDTO.getQuizDescription());
        quiz.setQuizDuration(quizDTO.getQuizDuration());
        quiz.setTotalQuestions(quizDTO.getTotalQuestions());
        quiz.setTotalMarks(quizDTO.getTotalMarks());
        quiz.setPassingMarks(quizDTO.getPassingMarks());
        quiz.setQuizStatus(quizDTO.getQuizStatus());

        Set<Question> questions = new HashSet<>(questionService.findAllByIds(quizDTO.getQuestionIds()));
        quiz.setQuestions(questions);

        // Actualizăm lecția asociată (opțional)
        Lesson lesson = lessonService.findById(quizDTO.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found with id " + quizDTO.getLessonId()));
        quiz.setLesson(lesson);

        return quizService.save(quiz);
    }

    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteById(id);
    }
}
