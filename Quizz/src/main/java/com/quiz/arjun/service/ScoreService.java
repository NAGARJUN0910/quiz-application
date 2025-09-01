package com.quiz.arjun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.arjun.entites.Score;
import com.quiz.arjun.repository.ScoreRepository;

import java.util.List;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository scoreRepository;

	public Score saveScore(Score score) {
		return scoreRepository.save(score);
	}

	public List<Score> findScoresByUserId(Long userId) {
		return scoreRepository.findByUserIdOrderByCompletedAtDesc(userId);
	}

	public List<Score> findAllScores() {
		return scoreRepository.findAll();
	}

	public List<Score> findScoresByQuizId(Long quizId) {
		return scoreRepository.findByQuizId(quizId);
	}
}
