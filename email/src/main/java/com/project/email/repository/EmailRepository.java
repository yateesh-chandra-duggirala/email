package com.project.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.project.email.model.Email;

@EnableJpaRepositories
public interface EmailRepository extends JpaRepository<Email, Long>{

}
