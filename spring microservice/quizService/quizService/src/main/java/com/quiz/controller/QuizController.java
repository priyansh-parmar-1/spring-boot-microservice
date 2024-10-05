package com.quiz.controller;

import com.quiz.entity.Quiz;
import com.quiz.response.Response;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/{id}")
    ResponseEntity<Response<Quiz>> get(@PathVariable Long id) {
        return quizService.getById(id);
    }

    @GetMapping
    ResponseEntity<Response<List<Quiz>>> getAll() {
        return quizService.getAll();
    }

    @PostMapping
    ResponseEntity<Response<Quiz>> create(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @PutMapping("/{id}")
    ResponseEntity<Response<Quiz>> updateQuizById(@RequestBody Quiz quiz) {
        return quizService.updateQuizById(quiz.getId(),quiz);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Response<Quiz>> deleteQuizById(@PathVariable Long id) {
        return quizService.deleteById(id);
    }
}
