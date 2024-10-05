package com.question.service.impl;

import com.question.entity.Question;
import com.question.repository.QuestionRepositry;
import com.question.response.Response;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepositry questionRepositry;

    @Override
    public ResponseEntity<Response<List<Question>>> getAll() {
        List<Question> allQuestion = questionRepositry.findAll();
        Response<List<Question>> response = new Response<>();
        if (!allQuestion.isEmpty()) {
            response.setStatus("success");
            response.setData(allQuestion);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("No data");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Question>> getById(Long id) {
        Optional<Question> questionOptional = questionRepositry.findById(id);
        Response<Question> response = new Response<>();
        if (questionOptional.isPresent()) {
            response.setStatus("success");
            response.setData(questionOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Question>> createQuestion(Question question) {
        Response<Question> response = new Response<>();
        if (!(question == null)) {
            response.setStatus("success");
            response.setData(questionRepositry.save(question));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Could not create");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<List<Question>>> getQuestionByQuizId(Long id) {
        List<Question> questionsByQuizId = questionRepositry.findByQuizId(id);
        Response<List<Question>> response = new Response<>();
        if (!questionsByQuizId.isEmpty()) {
            response.setStatus("success");
            response.setData(questionsByQuizId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Question>> deleteQuestionById(Long id) {
        Optional<Question> questionOptional = questionRepositry.findById(id);
        Response<Question> response = new Response<>();
        if (questionOptional.isPresent()) {
            questionRepositry.deleteById(id);
            response.setStatus("success");
            response.setData(questionOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Question>> updateQuestionById(Long id, Question newQue) {
        Optional<Question> questionOptional = questionRepositry.findById(id);
        Response<Question> response = new Response<>();
        if (questionOptional.isPresent()) {
            Question oldQue = questionOptional.get();
            oldQue.setQuestion(newQue.getQuestion());
            oldQue.setQuizId(newQue.getQuizId());
            questionRepositry.save(oldQue);
            response.setStatus("success");
            response.setData(oldQue);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
