package com.quizGpt.formManagement.ChatGpt;

import com.quizGpt.formManagement.ChatGpt.Entity.GptServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GptRepository extends JpaRepository<GptServiceResponse, Long> {

}