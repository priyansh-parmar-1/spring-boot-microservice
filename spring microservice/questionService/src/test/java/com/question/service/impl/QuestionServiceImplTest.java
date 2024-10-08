package com.question.service.impl;

import com.question.entity.Question;
import com.question.repository.QuestionRepositry;
import com.question.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    QuestionRepositry questionRepositry;

    @InjectMocks
    QuestionServiceImpl questionService;

    @Test
    void testGetAllSuccess() {
        Question question1 = new Question(1L, "test1", 1L);
        Question question2 = new Question(2L, "test2", 2L);
        List<Question> questions = Arrays.asList(question1, question2);
        when(questionRepositry.findAll()).thenReturn(questions);
        ResponseEntity<Response<List<Question>>> allQuestions = questionService.getAll();
        assertEquals(2, allQuestions.getBody().getData().size());
        assertEquals(HttpStatus.OK, allQuestions.getStatusCode());
        assertEquals(questions, allQuestions.getBody().getData());
    }

    @Test
    void testGetAllFailure() {
        List<Question> questions = new ArrayList<>();
        when(questionRepositry.findAll()).thenReturn(questions);
        ResponseEntity<Response<List<Question>>> allQuestions = questionService.getAll();
        List<Question> data = allQuestions.getBody().getData();
        assertEquals(HttpStatus.NOT_FOUND, allQuestions.getStatusCode());
        assertNull(allQuestions.getBody().getData());
    }

    @Test
    void testGetByIdSuccess() {
        Optional<Question> questionOptional = Optional.of(new Question(1L, "test", 1L));
        when(questionRepositry.findById(1L)).thenReturn(questionOptional);
        ResponseEntity<Response<Question>> responseEntity = questionService.getById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(questionOptional.get(), responseEntity.getBody().getData());
    }

    @Test
    void testGetByIdFailure() {
        Optional<Question> questionOptional = Optional.empty();
        when(questionRepositry.findById(1L)).thenReturn(questionOptional);
        ResponseEntity<Response<Question>> responseEntity = questionService.getById(1L);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getData());
    }

    @Test
    void testCreateQuestionSuccess() {
        Question question = new Question(1L, "test", 1L);
        when(questionRepositry.save(question)).thenReturn(question);
        ResponseEntity<Response<Question>> responseEntity = questionService.createQuestion(question);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(question,responseEntity.getBody().getData());
    }

    @Test
    void testCreateQuestionFailure() {
        Question question = null;
        ResponseEntity<Response<Question>> responseEntity = questionService.createQuestion(question);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, responseEntity.getStatusCode());
        assertEquals(question,responseEntity.getBody().getData());
    }

    @Test
    void testGetQuestionByQuizIdSuccess(){
        List<Question> questions = Arrays.asList(
                new Question(1L,"test",1L),
                new Question(2L,"test",1L)
        );
        when(questionRepositry.findByQuizId(1L)).thenReturn(questions);
        ResponseEntity<Response<List<Question>>> responseEntity = questionService.getQuestionByQuizId(1L);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(questions,responseEntity.getBody().getData());
    }

    @Test
    void testGetQuestionByQuizIdFailure(){
        List<Question> questions = new ArrayList<>();
        when(questionRepositry.findByQuizId(1L)).thenReturn(questions);
        ResponseEntity<Response<List<Question>>> responseEntity = questionService.getQuestionByQuizId(1L);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getData());
    }

    @Test
    void testDeleteQuestionByIdSuccess(){
        Optional<Question> questionOptional = Optional.of(new Question(1L,"test",1L));
        when(questionRepositry.findById(1L)).thenReturn(questionOptional);
        ResponseEntity<Response<Question>> responseEntity = questionService.deleteQuestionById(1L);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        verify(questionRepositry,times(1)).deleteById(1L);
    }

    @Test
    void testDeleteQuestionByIdFailure(){
        Optional<Question> questionOptional = Optional.empty();
        when(questionRepositry.findById(1L)).thenReturn(questionOptional);
        ResponseEntity<Response<Question>> responseEntity = questionService.deleteQuestionById(1L);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        verify(questionRepositry,times(0)).deleteById(1L);
    }
}