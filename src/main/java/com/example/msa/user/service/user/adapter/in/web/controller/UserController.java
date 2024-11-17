package com.example.msa.user.service.user.adapter.in.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignInRequest;
import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignUpRequest;
import com.example.msa.user.service.user.adapter.in.web.dto.response.MessageResponse;
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
    public ResponseEntity<MessageResponse> signUp(@RequestBody UserSignUpRequest body) {
        boolean signUpSuccess = userService.signUp(body.getUserId(), body.getPw(), body.getRole());

        if (!signUpSuccess) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new MessageResponse("회원가입 실패"));
        }
        return new ResponseEntity<>(new MessageResponse("회원가입 성공"), HttpStatus.OK);
    }

    @PostMapping("/v1/user/sign-in")
    public ResponseEntity<MessageResponse> signIn(@RequestBody UserSignInRequest body) {
        boolean signInSuccess = userService.signIn(body.getUserId(), body.getPw());

        if (!signInSuccess) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new MessageResponse("로그인 실패 : 잘못된 ID 또는 비밀번호"));
        }
        return new ResponseEntity<>(new MessageResponse("로그인 성공"), HttpStatus.OK);
    }
}
