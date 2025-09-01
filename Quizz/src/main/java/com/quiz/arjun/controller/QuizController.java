package com.quiz.arjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quiz.arjun.entites.Question;
import com.quiz.arjun.entites.Score;
import com.quiz.arjun.entites.User;
import com.quiz.arjun.service.QuizService;
import com.quiz.arjun.service.ScoreService;
import com.quiz.arjun.service.UserService;
import com.quiz.arjun.entites.Quiz;


import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private UserService userService;

	@GetMapping("/dashboard")
	public String dashboard(Authentication auth, Model model) {
		User user = userService.findByUsername(auth.getName());
		List<Score> userScores = scoreService.findScoresByUserId(user.getId());

		model.addAttribute("user", user);
		model.addAttribute("scores", userScores);
		return "user-dashboard";
	}

	@GetMapping("/quizzes")
	public String listQuizzes(Model model) {
		List<Quiz> quizzes = quizService.findAllQuizzes();
		model.addAttribute("quizzes", quizzes);
		return "quiz-list";
	}

	@GetMapping("/quiz/{id}")
	public String takeQuiz(@PathVariable Long id, Model model) {
		Quiz quiz = quizService.findQuizById(id).orElse(null);
		if (quiz == null) {
			return "redirect:/quizzes";
		}

		List<Question> questions = quizService.findQuestionsByQuizId(id);
		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questions);
		return "take-quiz";
	}

	@PostMapping("/quiz/{id}/submit")
	public String submitQuiz(@PathVariable Long id, @RequestParam Map<String, String> answers, Authentication auth,
			Model model) {
		Quiz quiz = quizService.findQuizById(id).orElse(null);
		if (quiz == null) {
			return "redirect:/quizzes";
		}

		List<Question> questions = quizService.findQuestionsByQuizId(id);
		int correctAnswers = 0;

		for (Question question : questions) {
			String userAnswer = answers.get("question_" + question.getId());
			if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
				correctAnswers++;
			}
		}

		User user = userService.findByUsername(auth.getName());
		Score score = new Score(user, quiz, correctAnswers, questions.size());
		scoreService.saveScore(score);

		model.addAttribute("score", score);
		model.addAttribute("quiz", quiz);
		return "quiz-result";
	}
}
