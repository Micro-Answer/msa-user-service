package com.example.msa.user.service.user.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <b> 역할: 유저 회원가입 요청 </b>
 * <p>
 * - HTTP 요청 바디에 매핑
 * </p>
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {
	private String id;
	private String email;
	private String pw;
	private String role;
	private int age;
}
