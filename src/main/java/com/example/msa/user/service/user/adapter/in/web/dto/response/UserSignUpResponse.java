package com.example.msa.user.service.user.adapter.in.web.dto.response;

import com.example.msa.user.service.user.application.domain.User;

import lombok.Getter;

/**
 * <b> 역할: 유저 회원가입 응답 </b>
 * <p>
 * - HTTP 응답 바디에 매핑
 * </p>
 */
@Getter
public class UserSignUpResponse {
	private String id;
	private String email;
	private String pw;
	private String role;
	private int age;

	public UserSignUpResponse(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.pw = user.getPw();
		this.role = user.getRole();
		this.age = user.getAge();
	}
}
