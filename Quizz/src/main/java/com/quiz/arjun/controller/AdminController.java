package com.quiz.arjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.quiz.arjun.entites.Question;
import com.quiz.arjun.entites.Score;
import com.quiz.arjun.entites.User;
import com.quiz.arjun.service.QuizService;
import com.quiz.arjun.service.ScoreService;
import com.quiz.arjun.service.UserService;
import com.quiz.arjun.entites.Quiz;   

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private QuizService quizService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private UserService userService;

	@GetMapping("/dashboard")
	public String adminDashboard(Model model) {
		List<Quiz> quizzes = quizService.findAllQuizzes();
		List<User> users = userService.findAllUsers();

		model.addAttribute("quizzes", quizzes);
		model.addAttribute("users", users);
		return "admin/admin-dashboard";
	}

	@GetMapping("/quizzes")
	public String manageQuizzes(Model model) {
		List<Quiz> quizzes = quizService.findAllQuizzes();
		model.addAttribute("quizzes", quizzes);
		model.addAttribute("quiz", new Quiz());
		return "admin/manage-quizzes";
	}

	@PostMapping("/quiz/save")
	public String saveQuiz(@Valid @ModelAttribute("quiz") Quiz quiz, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Quiz> quizzes = quizService.findAllQuizzes();
			model.addAttribute("quizzes", quizzes);
			return "admin/manage-quizzes";
		}

		quizService.saveQuiz(quiz);
		return "redirect:/admin/quizzes";
	}

	@GetMapping("/quiz/{id}/questions")
	public String manageQuestions(@PathVariable Long id, Model model) {
		Quiz quiz = quizService.findQuizById(id).orElse(null);
		if (quiz == null) {
			return "redirect:/admin/quizzes";
		}

		List<Question> questions = quizService.findQuestionsByQuizId(id);
		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questions);
		model.addAttribute("question", new Question());
		return "admin/manage-questions";
	}

	@PostMapping("/quiz/{quizId}/question/save")
	public String saveQuestion(@PathVariable Long quizId, @Valid @ModelAttribute("question") Question question,
			BindingResult result, Model model) {
		Quiz quiz = quizService.findQuizById(quizId).orElse(null);
		if (quiz == null) {
			return "redirect:/admin/quizzes";
		}

		if (result.hasErrors()) {
			List<Question> questions = quizService.findQuestionsByQuizId(quizId);
			model.addAttribute("quiz", quiz);
			model.addAttribute("questions", questions);
			return "admin/manage-questions";
		}

		question.setQuiz(quiz);
		quizService.saveQuestion(question);
		return "redirect:/admin/quiz/" + quizId + "/questions";
	}

	@GetMapping("/quiz/delete/{id}")
	public String deleteQuiz(@PathVariable Long id) {
		quizService.deleteQuiz(id);
		return "redirect:/admin/quizzes";
	}

	@GetMapping("/question/delete/{id}")
	public String deleteQuestion(@PathVariable Long id) {
		Question question = quizService.findQuestionById(id).orElse(null);
		Long quizId = question != null ? question.getQuiz().getId() : null;
		quizService.deleteQuestion(id);
		return quizId != null ? "redirect:/admin/quiz/" + quizId + "/questions" : "redirect:/admin/quizzes";
	}

	@GetMapping("/scores")
	public String viewAllScores(Model model) {
		List<Score> scores = scoreService.findAllScores();
		model.addAttribute("scores", scores);
		return "admin/user-scores";
	}
}