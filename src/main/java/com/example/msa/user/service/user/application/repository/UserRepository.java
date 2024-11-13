package com.example.msa.user.service.user.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.msa.user.service.user.application.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
