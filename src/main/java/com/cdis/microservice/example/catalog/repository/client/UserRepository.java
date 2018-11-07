package com.cdis.microservice.example.catalog.repository.client;

import com.cdis.microservice.example.catalog.model.client.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
