package com.example.msa.user.service.user.adapter.out.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.msa.user.service.user.application.model.domain.User;

import lombok.RequiredArgsConstructor;

/**
 * <b> 유저 영속성 어댑터 </b>
 */
@Component
@RequiredArgsConstructor
public class PersistenceAdapter {
	private final List<User> users = new ArrayList<>();

	/**
	 * <b> 유저의 추가 정보 저장 </b>
	 * <p>
	 * - 임시로 이메일, 비밀번호, 역할로 지정
	 * </p>
	 */
	public void saveUser(User user) {
		users.add(user);
	}

	/**
	 * <b> id로 유저가 존재하는지 확인 </b>
	 */
	public boolean existsById(String id) {
		return users.stream()
			.anyMatch(user -> user.getId().equals(id));
	}
}
