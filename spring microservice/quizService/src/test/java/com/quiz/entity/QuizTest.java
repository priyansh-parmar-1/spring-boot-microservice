package com.quiz.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuizTest {

    @Test
    void testGetterSetterWithNoArgsConstructor() {
        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setTitle("test");
        quiz.setQuestions(List.of(
                new Question(1L, "test", 1L),
                new Question(2L, "test", 1L))
        );
        assertEquals(1L, quiz.getId());
        assertEquals("test",quiz.getTitle());
        assertEquals(2,quiz.getQuestions().size());
    }

    @Test
    void testQuizBuilder(){
        Quiz quiz = Quiz
                .builder()
                .id(1L)
                .title("test")
                .questions(List.of(
                new Question(1L, "test", 1L),
                new Question(2L, "test", 1L)))
                .build();
        assertEquals(1L, quiz.getId());
        assertEquals("test",quiz.getTitle());
        assertEquals(2,quiz.getQuestions().size());
    }
}