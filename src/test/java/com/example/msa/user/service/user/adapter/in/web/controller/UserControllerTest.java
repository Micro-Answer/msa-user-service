package com.example.msa.user.service.user.adapter.in.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignInRequest;
import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignUpRequest;
import com.example.msa.user.service.user.application.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private UserSignUpRequest signUpRequest;
    private UserSignInRequest signInRequest;

    @BeforeEach
    void setUp() {
        signUpRequest = UserSignUpRequest.builder()
            .userId("test1234")
            .pw("1234")
            .role("USER")
            .build();

        signInRequest = new UserSignInRequest("test1234", "1234");
    }

    @Test
    @DisplayName("회원가입 요청에 대한 성공 응답 테스트")
    void testSignUp_Success() throws Exception {
        Mockito.when(userService.signUp(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()))
            .thenReturn(true);

        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        mockMvc.perform(post("/api/v1/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("회원가입 성공"));
    }

    @Test
    @DisplayName("회원가입 요청 실패 테스트")
    void testSignUp_Failure() throws Exception {
        Mockito.when(userService.signUp(
                Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString()))
            .thenReturn(false);

        String jsonRequest = objectMapper.writeValueAsString(signUpRequest);

        mockMvc.perform(post("/api/v1/user/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.result").value("회원가입 실패"));
    }

    @Test
    @DisplayName("로그인 요청에 대한 성공 응답 테스트")
    void testSignIn_Success() throws Exception {
        Mockito.when(userService.signIn(
                Mockito.anyString(),
                Mockito.anyString()))
            .thenReturn(true);

        String jsonRequest = objectMapper.writeValueAsString(signInRequest);

        mockMvc.perform(post("/api/v1/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result").value("로그인 성공"));
    }

    @Test
    @DisplayName("로그인 요청 실패 테스트")
    void testSignIn_Failure() throws Exception {
        Mockito.when(userService.signIn(
                Mockito.anyString(),
                Mockito.anyString()))
            .thenReturn(false);

        String jsonRequest = objectMapper.writeValueAsString(signInRequest);

        mockMvc.perform(post("/api/v1/user/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.result").value("로그인 실패 : 잘못된 ID 또는 비밀번호"));
    }
}
