package com.quizGpt.formManagement.Quiz.Repository;

import com.quizGpt.formManagement.Quiz.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
