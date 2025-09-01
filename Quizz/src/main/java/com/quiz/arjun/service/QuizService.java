package com.quiz.arjun.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.arjun.entites.Question;
import com.quiz.arjun.entites.Quiz;
import com.quiz.arjun.repository.QuestionRepository;
import com.quiz.arjun.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }
    
    public Optional<Quiz> findQuizById(Long id) {
        return quizRepository.findById(id);
    }
    
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
    
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
    
    public List<Question> findQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }
    
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    
    public Optional<Question> findQuestionById(Long id) {
        return questionRepository.findById(id);
    }
}
