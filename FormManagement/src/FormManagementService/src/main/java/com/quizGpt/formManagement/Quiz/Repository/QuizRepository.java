package com.quizGpt.formManagement.Quiz.Repository;


import com.quizGpt.formManagement.Quiz.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}