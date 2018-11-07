package com.cdis.microservice.example.auth.repository;

import com.cdis.microservice.example.auth.model.UserLogInAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogInAttemptRepository extends JpaRepository<UserLogInAttempt, Long> {
}
