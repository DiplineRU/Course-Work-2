package com.example.CourseWork2.Service;

import com.example.CourseWork2.Exception.QuestionAlreadyExistsException;
import com.example.CourseWork2.Exception.QuestionAreEmptyException;
import com.example.CourseWork2.Exception.QuestionNotFoundException;
import com.example.CourseWork2.Question;
import com.example.CourseWork2.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class QuestionServiceImpl implements QuestionService {
    public final Set<Question> questions = new HashSet<>();

    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question find(String question) {
        for (Question element : questions) {
            if(element.getQuestion().equals(question)){
                return element;
            }
        }
        return null;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new QuestionAreEmptyException();
        }
        int count = random.nextInt(questions.size());
        int counter = 0;
        for (Question question : questions) {
            if (counter == count) {
                return question;
            }
            counter++;
        }
        return null;
    }
}