package com.example.CourseWork2;

import com.example.CourseWork2.Exception.NotEnoughQuestionsException;
import com.example.CourseWork2.Service.ExaminerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5"),
            new Question("Вопрос 6", "Ответ 6")
    );

    @BeforeEach
    public void beforeEach(){

        when(questionService.getAll()).thenReturn(questions);
    }


    @Test
    public void getQuestionsNegativeTest(){
        when(questionService.getAll()).thenReturn(questions);

        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(() -> examinerService.getQuestion(questions.size() + 1));
    }
    @Test
    public void getQuestionsPositiveTest(){
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 5", "Ответ 5")
        );
        Assertions.assertTrue(examinerService.getQuestion(4).containsAll(List.of(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 4", "Ответ 4"),
                new Question("Вопрос 5", "Ответ 5"))
        ));
    }
}
