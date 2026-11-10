package com.onlinetest.service;

import com.onlinetest.model.*;
import com.onlinetest.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Service
public class QuizService {
    private final QuizRepository quizzes;
    private final UserRepository users;
    private final AttemptRepository attempts;

    public QuizService(QuizRepository quizzes, UserRepository users, AttemptRepository attempts) {
        this.quizzes = quizzes;
        this.users = users;
        this.attempts = attempts;
    }

    @Transactional
    public Attempt submit(Long quizId, String email, Map<String, String> answers) {
        Quiz quiz = quizzes.findById(quizId)
            .filter(Quiz::isActive)
            .orElseThrow(() -> new IllegalArgumentException("Quiz not found or inactive."));
        AppUser user = users.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User account not found."));

        if (quiz.getQuestions().isEmpty())
            throw new IllegalArgumentException("This quiz has no questions yet.");

        int score = 0;
        for (Question question : quiz.getQuestions()) {
            String answer = answers.get("q" + question.getId());
            if (answer != null && question.getCorrectAnswer().equals(answer))
                score++;
        }

        Attempt attempt = new Attempt();
        attempt.setUser(user);
        attempt.setQuiz(quiz);
        attempt.setScore(score);
        attempt.setTotalQuestions(quiz.getQuestions().size());
        return attempts.save(attempt);
    }
}
