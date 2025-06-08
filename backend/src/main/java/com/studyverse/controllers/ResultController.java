package com.studyverse.controllers;

import com.studyverse.dto.QuizSubmissionDTO;
import com.studyverse.models.Question;
import com.studyverse.models.Quiz;
import com.studyverse.models.User;
import com.studyverse.services.QuizService;
import com.studyverse.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Map<String, Object> evaluateQuiz(@RequestBody QuizSubmissionDTO submission) {
        Quiz quiz = quizService.findById(submission.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        User user = userService.findById(submission.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<Long, String> answers = submission.getAnswers();

        int correctCount = 0;
        for (Question q : quiz.getQuestions()) {
            String userAnswer = answers.get(q.getId());
            if (userAnswer != null && userAnswer.equalsIgnoreCase(q.getCorrectAnswer())) {
                correctCount++;
            }
        }

        int score = (int) Math.round(((double) correctCount / quiz.getQuestions().size()) * 10);
        boolean passed = score >= quiz.getPassingMarks();

        // ✅ Dacă scorul este 10, actualizăm scorul pe subiect
        if (score == 10 && quiz.getLesson() != null) {
            String subject = quiz.getLesson().getSubject().toLowerCase();

            switch (subject) {
                case "matematica" -> user.setScoreMath(user.getScoreMath() + 10);
                case "chimie" -> user.setScoreChemistry(user.getScoreChemistry() + 10);
                // poți adăuga mai multe subiecte aici dacă ai nevoie
            }

            userService.save(user); // salvăm scorul modificat
        }

        return Map.of(
                "score", score,
                "passed", passed,
                "subject", quiz.getLesson() != null ? quiz.getLesson().getSubject() : null);
    }
}
