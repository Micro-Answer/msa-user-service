package com.example.msa.user.service.user.adapter.in.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInRequest {
	private String userId;
	private String pw;

	public UserSignInRequest(String userId, String pw) {
		this.userId = userId;
		this.pw = pw;
	}
}
