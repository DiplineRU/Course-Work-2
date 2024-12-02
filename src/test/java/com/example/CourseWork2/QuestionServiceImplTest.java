package com.example.CourseWork2;

import com.example.CourseWork2.Exception.QuestionAlreadyExistsException;
import com.example.CourseWork2.Exception.QuestionAreEmptyException;
import com.example.CourseWork2.Exception.QuestionNotFoundException;
import com.example.CourseWork2.Service.QuestionServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionServiceImplTest {

    private QuestionService questionService = new QuestionServiceImpl();

    @Test
    public void testAdd() {
        Question question = new Question("2+2?", "4");
        Question addedQuestion = questionService.add(question);
        assertEquals(question, addedQuestion);
    }

    @Test
    public void testAddExistingQuestion() {
        Question question = new Question("2+2?", "4");
        questionService.add(question);
        assertThrows(QuestionAlreadyExistsException.class, () -> {
            questionService.add(question);
        });
    }

    @Test
    public void testRemove() {
        Question question = new Question("2+2?", "4");
        questionService.add(question);
        Question removedQuestion = questionService.remove(question);
        assertEquals(question, removedQuestion);
    }

    @Test
    public void testRemoveNonExistingQuestion() {
        Question question = new Question("2+2?", "4");
        assertThrows(QuestionNotFoundException.class, () -> {
            questionService.remove(question);
        });
    }

    @Test
    public void testGetAll() {
        Question question1 = new Question("2+2?", "4");
        Question question2 = new Question("3+3?", "6");
        questionService.add(question1);
        questionService.add(question2);

        Collection<Question> allQuestions = questionService.getAll();
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
    }

    @Test
    public void testFind() {
        Question question = new Question("2+2?", "4");
        questionService.add(question);
        Question foundQuestion = questionService.find("2+2?");
        assertEquals(question, foundQuestion);
    }

    @Test
    public void testFindNonExistingQuestion() {
        Question notInServiceQuestion = questionService.find("3+3?");
        assertNull(notInServiceQuestion);
    }

    @Test
    public void testGetRandomQuestion() {
        Question question1 = new Question("2+2?", "4");
        Question question2 = new Question("3+3?", "6");
        questionService.add(question1);
        questionService.add(question2);

        Question randomQuestion = questionService.getRandomQuestion();
        assertTrue(randomQuestion.equals(question1) || randomQuestion.equals(question2));
    }

    @Test
    public void testGetRandomQuestionWhenQuestionsEmpty() {
        assertThrows(QuestionAreEmptyException.class, () -> {
            questionService.getRandomQuestion();
        });
    }
}
