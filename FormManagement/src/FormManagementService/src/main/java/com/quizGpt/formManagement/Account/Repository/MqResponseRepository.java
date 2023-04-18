package com.quizGpt.formManagement.Account.Repository;


import com.quizGpt.formManagement.Account.Entity.MqResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MqResponseRepository extends JpaRepository<MqResponse, String> {
    MqResponse findFirstByResponseContaining(String Response);
}
