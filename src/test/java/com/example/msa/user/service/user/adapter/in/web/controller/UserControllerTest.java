package com.example.msa.user.service.user.adapter.in.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignUpRequest;
import com.example.msa.user.service.user.adapter.in.web.dto.response.UserSignUpResponse;
import com.example.msa.user.service.user.application.domain.User;
import com.example.msa.user.service.user.application.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

	private UserSignUpRequest signUpRequest;

	@BeforeEach
	void setUp() {
		signUpRequest = UserSignUpRequest.builder()
			.id("1")
			.email("test1234@google.com")
			.pw("1234")
			.role("USER")
			.age(20)
			.build();
	}

	@Test
	@DisplayName("회원가입 요청에 대한 정상 응답 테스트")
	void testSignUp_Success() throws Exception {
		User user = User.builder()
			.id("1")
			.email("test1234@google.com")
			.pw("1234")
			.role("USER")
			.age(20)
			.build();
		UserSignUpResponse signUpResponse = new UserSignUpResponse(user);

		Mockito.when(userService.signUp(Mockito.any(UserSignUpRequest.class))).thenReturn(signUpResponse);
		String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

		mockMvc.perform(post("/api/v1/user/sign-up")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value("1"))
			.andExpect(jsonPath("$.email").value("test1234@google.com"))
			.andExpect(jsonPath("$.pw").value("1234"))
			.andExpect(jsonPath("$.role").value("USER"))
			.andExpect(jsonPath("$.age").value(20));
	}
}
