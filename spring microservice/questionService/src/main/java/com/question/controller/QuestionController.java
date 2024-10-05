package com.question.controller;

import com.question.entity.Question;
import com.question.response.Response;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/{id}")
    ResponseEntity<Response<Question>> get(@PathVariable Long id) {
        return questionService.getById(id);
    }

    @GetMapping("/quiz/{id}")
    ResponseEntity<Response<List<Question>>> getByQuizId(@PathVariable Long id) {
        return questionService.getQuestionByQuizId(id);
    }

    @GetMapping
    ResponseEntity<Response<List<Question>>> getAll() {
        return questionService.getAll();
    }

    @PostMapping
    ResponseEntity<Response<Question>> create(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response<Question>> deleteById(@PathVariable Long id) {
        return questionService.deleteQuestionById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<Response<Question>> updateQuestionById(@RequestBody Question que) {
        return questionService.updateQuestionById(que.getId(),que);
    }
}
