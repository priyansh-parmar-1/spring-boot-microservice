package com.quiz.service.impl;

import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.response.Response;
import com.quiz.service.QuestionClient;
import com.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionClient questionClient;

    @Override
    public ResponseEntity<Response<List<Quiz>>> getAll() {
        List<Quiz> allQuiz = quizRepository.findAll();

        List<Quiz> quizListWithQuestions = allQuiz.stream().map(quiz -> {
            Response<List<Question>> questionsForQuiz = questionClient.getQuestionsForQuiz(quiz.getId());
            quiz.setQuestions(questionsForQuiz.getData());
            return quiz;
        }).toList();

        Response<List<Quiz>> response = new Response<>();
        if (!allQuiz.isEmpty()) {
            response.setStatus("success");
            response.setData(quizListWithQuestions);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("No data");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Quiz>> getById(Long id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        Response<Quiz> response = new Response<>();
        if (quizOptional.isPresent()) {

            Quiz quiz = quizOptional.get();
            Response<List<Question>> questionsForQuiz = questionClient.getQuestionsForQuiz(quiz.getId());
            quiz.setQuestions(questionsForQuiz.getData());
            response.setStatus("success");
            response.setData(quiz);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Quiz>> createQuiz(Quiz quiz) {
        Response<Quiz> response = new Response<>();
        if (!(quiz == null)) {
            response.setStatus("success");
            response.setData(quizRepository.save(quiz));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Could not create");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Quiz>> deleteById(Long id) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        Response<Quiz> response = new Response<>();
        if (quizOptional.isPresent()) {
            quizRepository.deleteById(id);
            response.setStatus("success");
            response.setData(quizOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Response<Quiz>> updateQuizById(Long id, Quiz newQuiz) {
        Optional<Quiz> quizOptional = quizRepository.findById(id);
        Response<Quiz> response = new Response<>();
        if (quizOptional.isPresent()) {
            Quiz oldquiz = quizOptional.get();
            oldquiz.setTitle(newQuiz.getTitle());
            oldquiz.setQuestions(newQuiz.getQuestions());
            quizRepository.save(oldquiz);
            response.setStatus("success");
            response.setData(oldquiz);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus("failure");
            response.setError("Not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
