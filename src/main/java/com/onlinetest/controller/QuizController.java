package com.onlinetest.controller;

import com.onlinetest.model.Quiz;
import com.onlinetest.repository.QuizRepository;
import com.onlinetest.service.QuizService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Map;

@Controller
public class QuizController {
    private final QuizRepository quizzes;
    private final QuizService quizService;

    public QuizController(QuizRepository quizzes, QuizService quizService) {
        this.quizzes = quizzes;
        this.quizService = quizService;
    }

    @GetMapping("/quiz/{id}")
    public String viewQuiz(@PathVariable Long id, Model model) {
        Quiz quiz = quizzes.findById(id)
            .filter(Quiz::isActive)
            .orElseThrow(() -> new IllegalArgumentException("Quiz not found."));
        model.addAttribute("quiz", quiz);
        return "quiz";
    }

    @PostMapping("/quiz/{id}/submit")
    public String submit(@PathVariable Long id,
                         @RequestParam Map<String, String> answers,
                         Authentication auth, RedirectAttributes redirect) {
        try {
            var attempt = quizService.submit(id, auth.getName(), answers);
            redirect.addFlashAttribute("result",
                "You scored " + attempt.getScore() + " out of " + attempt.getTotalQuestions() + ".");
        } catch (IllegalArgumentException ex) {
            redirect.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/dashboard";
    }
}
