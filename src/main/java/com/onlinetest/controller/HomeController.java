package com.onlinetest.controller;

import com.onlinetest.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final QuizRepository quizzes;
    private final AttemptRepository attempts;

    public HomeController(QuizRepository quizzes, AttemptRepository attempts) {
        this.quizzes = quizzes;
        this.attempts = attempts;
    }

    @GetMapping("/")
    public String home() { return "redirect:/dashboard"; }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model model) {
        model.addAttribute("quizzes", quizzes.findByActiveTrueOrderByCreatedAtDesc());
        model.addAttribute("attempts",
            attempts.findTop20ByUserEmailOrderBySubmittedAtDesc(auth.getName()));
        model.addAttribute("email", auth.getName());
        model.addAttribute("isAdmin", auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        return "dashboard";
    }
}
