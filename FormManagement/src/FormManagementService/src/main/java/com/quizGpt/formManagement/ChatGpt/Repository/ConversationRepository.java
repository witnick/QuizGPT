package com.quizGpt.formManagement.ChatGpt.Repository;

import com.quizGpt.formManagement.ChatGpt.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}