package com.question.service.impl;

import com.question.entity.Question;
import com.question.repository.QuestionRepositry;
import com.question.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        List<Question> questions = Arrays.asList(question1,question2);
        when(questionRepositry.findAll()).thenReturn(questions);
        ResponseEntity<Response<List<Question>>> allQuestions = questionService.getAll();
        List<Question> data = allQuestions.getBody().getData();
        assertEquals(questions,data);
    }
}