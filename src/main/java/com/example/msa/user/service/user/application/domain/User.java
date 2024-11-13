package com.example.msa.user.service.user.application.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	// TODO - String은 AI가 적용이 안되는데 어떻게 처리해야할까요?
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String email;
	private String pw;
	private String role;
	private int age;
}
