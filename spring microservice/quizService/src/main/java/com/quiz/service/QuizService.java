package com.quiz.service;

import java.util.List;

import com.quiz.entity.Quiz;
import com.quiz.response.Response;
import org.springframework.http.ResponseEntity;

public interface QuizService {

    ResponseEntity<Response<List<Quiz>>> getAll();

    ResponseEntity<Response<Quiz>> getById(Long id);

    ResponseEntity<Response<Quiz>> createQuiz(Quiz quiz);

    ResponseEntity<Response<Quiz>> deleteById(Long id);

    ResponseEntity<Response<Quiz>> updateQuizById(Long id, Quiz newQue);
}
