package com.example.msa.user.service.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.msa.user.service.user.adapter.in.web.dto.request.UserSignUpRequest;
import com.example.msa.user.service.user.adapter.in.web.dto.response.UserSignUpResponse;
import com.example.msa.user.service.user.adapter.out.db.PersistenceAdapter;
import com.example.msa.user.service.user.application.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <b> 유저 서비스 </b>
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
	private final PersistenceAdapter persistenceAdapter;

	/**
	 * <b> 유저 회원가입 로직 수행 </b>
	 *
	 * @param id 아이디
	 * @param pw 비밀번호
	 * @param role 역할
	 * @return 회원가입 성공 여부
	 */
	public UserSignUpResponse signUp(UserSignUpRequest request) {
		User user = User.builder()
			.id(request.getId())
			.email(request.getEmail())
			.pw(request.getPw())
			.role(request.getRole())
			.age(request.getAge())
			.build();
		persistenceAdapter.saveUser(user);

		return new UserSignUpResponse(user);
	}
}
