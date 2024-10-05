package com.question.service;

import com.question.entity.Question;
import com.question.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    ResponseEntity<Response<List<Question>>> getAll();

    ResponseEntity<Response<Question>> getById(Long id);

    ResponseEntity<Response<Question>> createQuestion(Question question);

    ResponseEntity<Response<List<Question>>> getQuestionByQuizId(Long id);

    ResponseEntity<Response<Question>> deleteQuestionById(Long id);

    ResponseEntity<Response<Question>> updateQuestionById(Long id,Question newQue);
}
