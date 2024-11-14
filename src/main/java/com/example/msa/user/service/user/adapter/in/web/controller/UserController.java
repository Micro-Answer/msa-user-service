package com.example.msa.user.service.user.adapter.in.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignUpRequest;
import com.example.msa.user.service.user.adapter.in.web.dto.response.UserSignUpResponse;
import com.example.msa.user.service.user.application.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * <b> 유저 컨트롤러 </b>
 * <p>
 * - 유저 정보 관리
 * </p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
	private final UserService userService;

	@PostMapping("/v1/user/sign-up")
	public ResponseEntity<UserSignUpResponse> signUp(@RequestBody UserSignUpRequest body) {
		boolean signUpSuccess = userService.signUp(body.getId(), body.getUserId(), body.getPw(), body.getRole());

		if (!signUpSuccess) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new UserSignUpResponse("회원가입 실패"));
		}
		return new ResponseEntity<>(new UserSignUpResponse("회원가입 성공"), HttpStatus.OK);
	}
}
