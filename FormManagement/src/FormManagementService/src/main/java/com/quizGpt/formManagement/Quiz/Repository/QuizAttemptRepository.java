package com.quizGpt.formManagement.Quiz.Repository;

import com.quizGpt.formManagement.Quiz.Entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

}