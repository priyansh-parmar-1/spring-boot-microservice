package com.question.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    @DisplayName("Question model test")
    @Test
    void testQuestion() {
        Question questionEmpty = new Question();
        Question question = new Question(1L, "test", 1L);
        questionEmpty.setId(2L);
        questionEmpty.setQuestion("test1");
        questionEmpty.setQuizId(2L);

        assertEquals(1L, question.getId());
        assertEquals("test", question.getQuestion());
        assertEquals(1L, question.getQuizId());

    }

    @Test
    void testQuestionBuilder() {
        Question questionEmpty = new Question();
        Question question = Question.builder()
                .id(1L)
                .question("test")
                .quizId(1L)
                .build();

        assertEquals(1L, question.getId());
        assertEquals("test", question.getQuestion());
        assertEquals(1L, question.getQuizId());

    }
}