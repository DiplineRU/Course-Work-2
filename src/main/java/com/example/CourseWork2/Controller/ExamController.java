package com.example.CourseWork2.Controller;

import com.example.CourseWork2.ExamService;
import com.example.CourseWork2.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestion(@PathVariable int amount) {
        return examService.getQuestion(amount);
    }


}
