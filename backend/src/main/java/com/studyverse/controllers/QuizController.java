package com.studyverse.controllers;

import com.studyverse.dto.QuizDTO;
import com.studyverse.dto.QuizWithQuestionsDTO;
import com.studyverse.dto.QuestionDTO;
import com.studyverse.exceptions.LessonNotFoundException;
import com.studyverse.exceptions.QuizNotFoundException;
import com.studyverse.models.Lesson;
import com.studyverse.models.Question;
import com.studyverse.models.Quiz;
import com.studyverse.services.LessonService;
import com.studyverse.services.QuestionService;
import com.studyverse.services.QuizService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LessonService lessonService;

    // ✅ GET all quizzes (poate fi folosit pentru admin)
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.findAll();
    }

    // ✅ POST create quiz
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

        Set<Question> questions = new HashSet<>(questionService.findAllByIds(quizDTO.getQuestionIds()));
        quiz.setQuestions(questions);

        Lesson lesson = lessonService.findById(quizDTO.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found with id " + quizDTO.getLessonId()));
        quiz.setLesson(lesson);

        return quizService.save(quiz);
    }

    // ✅ GET quiz by ID (cu întrebări DTO simplificat)
    @GetMapping("/{id}")
    public QuizWithQuestionsDTO getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id " + id));

        QuizWithQuestionsDTO dto = new QuizWithQuestionsDTO();
        dto.setId(quiz.getId());
        dto.setQuizName(quiz.getQuizName());
        dto.setQuizDescription(quiz.getQuizDescription());
        dto.setQuizDuration(quiz.getQuizDuration());
        dto.setTotalQuestions(quiz.getTotalQuestions());
        dto.setTotalMarks(quiz.getTotalMarks());
        dto.setPassingMarks(quiz.getPassingMarks());
        dto.setQuizStatus(quiz.getQuizStatus());

        List<QuestionDTO> questionDTOs = quiz.getQuestions().stream().map(q -> {
            QuestionDTO qDto = new QuestionDTO();
            qDto.setId(q.getId());
            qDto.setQuestion(q.getQuestion());
            qDto.setOptionA(q.getOptionA());
            qDto.setOptionB(q.getOptionB());
            qDto.setOptionC(q.getOptionC());
            qDto.setOptionD(q.getOptionD());
            return qDto;
        }).collect(Collectors.toList());

        dto.setQuestions(questionDTOs);
        return dto;
    }

    // ✅ PUT update quiz
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

        Lesson lesson = lessonService.findById(quizDTO.getLessonId())
                .orElseThrow(() -> new LessonNotFoundException("Lesson not found with id " + quizDTO.getLessonId()));
        quiz.setLesson(lesson);

        return quizService.save(quiz);
    }

    // ✅ DELETE quiz
    @DeleteMapping("/{id}")
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteById(id);
    }

    // ✅ GET quizzes by subject (ex: "matematica")
    @GetMapping("/by-subject/{subject}")
    public List<QuizWithQuestionsDTO> getQuizzesBySubject(@PathVariable String subject) {
        List<Lesson> lessons = lessonService.findAllBySubject(subject);
        List<QuizWithQuestionsDTO> result = new ArrayList<>();

        for (Lesson lesson : lessons) {
            if (lesson.getQuizzes() != null) {
                for (Quiz quiz : lesson.getQuizzes()) {
                    QuizWithQuestionsDTO dto = new QuizWithQuestionsDTO();
                    dto.setId(quiz.getId());
                    dto.setQuizName(quiz.getQuizName());
                    dto.setQuizDescription(quiz.getQuizDescription());
                    dto.setQuizDuration(quiz.getQuizDuration());
                    dto.setTotalQuestions(quiz.getTotalQuestions());
                    dto.setTotalMarks(quiz.getTotalMarks());
                    dto.setPassingMarks(quiz.getPassingMarks());
                    dto.setQuizStatus(quiz.getQuizStatus());

                    dto.setQuestions(quiz.getQuestions().stream().map(q -> {
                        QuestionDTO qDto = new QuestionDTO();
                        qDto.setId(q.getId());
                        qDto.setQuestion(q.getQuestion());
                        qDto.setOptionA(q.getOptionA());
                        qDto.setOptionB(q.getOptionB());
                        qDto.setOptionC(q.getOptionC());
                        qDto.setOptionD(q.getOptionD());
                        return qDto;
                    }).collect(Collectors.toList()));

                    result.add(dto);
                }
            }
        }

        return result;
    }
}
