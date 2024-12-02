package com.example.CourseWork2.Service;

import com.example.CourseWork2.ExamService;
import com.example.CourseWork2.Exception.NotEnoughQuestionsException;
import com.example.CourseWork2.Question;
import com.example.CourseWork2.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExamService {
    public final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new NotEnoughQuestionsException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            Question question = questionService.getRandomQuestion();
            questions.add(question);
        }
        return questions;
    }
}