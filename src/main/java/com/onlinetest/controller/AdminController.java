package com.onlinetest.controller;

import com.onlinetest.model.*;
import com.onlinetest.repository.QuizRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final QuizRepository quizzes;

    public AdminController(QuizRepository quizzes) {
        this.quizzes = quizzes;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("quizzes", quizzes.findAll());
        return "admin";
    }

    @PostMapping("/quizzes")
    public String createQuiz(@RequestParam String title,
                             @RequestParam(required = false) String description) {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Quiz title is required.");
        Quiz quiz = new Quiz();
        quiz.setTitle(title.trim());
        quiz.setDescription(description == null ? "" : description.trim());
        quizzes.save(quiz);
        return "redirect:/admin";
    }

    @PostMapping("/quizzes/{id}/questions")
    public String addQuestion(@PathVariable Long id,
                              @RequestParam String questionText,
                              @RequestParam String options,
                              @RequestParam String correctAnswer) {
        Quiz quiz = quizzes.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Quiz not found."));
        List<String> choices = Arrays.stream(options.split("\\R"))
            .map(String::trim).filter(s -> !s.isEmpty()).distinct().toList();
        if (questionText.isBlank() || choices.size() < 2 || choices.size() > 6)
            throw new IllegalArgumentException("Provide a question and 2 to 6 distinct options.");
        if (!choices.contains(correctAnswer))
            throw new IllegalArgumentException("Correct answer must exactly match one option.");

        Question question = new Question();
        question.setQuestionText(questionText.trim());
        question.setOptions(choices);
        question.setCorrectAnswer(correctAnswer);
        quiz.addQuestion(question);
        quizzes.save(quiz);
        return "redirect:/admin";
    }

    @PostMapping("/quizzes/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        Quiz quiz = quizzes.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Quiz not found."));
        quiz.setActive(!quiz.isActive());
        quizzes.save(quiz);
        return "redirect:/admin";
    }
}
