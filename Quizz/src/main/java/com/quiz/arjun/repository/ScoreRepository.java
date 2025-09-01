package com.quiz.arjun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.arjun.entites.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
	List<Score> findByUserIdOrderByCompletedAtDesc(Long userId);

	List<Score> findByQuizId(Long quizId);
}
