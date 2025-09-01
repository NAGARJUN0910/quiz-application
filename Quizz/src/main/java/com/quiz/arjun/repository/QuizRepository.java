package com.quiz.arjun.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.arjun.entites.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
