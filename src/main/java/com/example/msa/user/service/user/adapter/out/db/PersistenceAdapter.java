package com.example.msa.user.service.user.adapter.out.db;

import org.springframework.stereotype.Component;

import com.example.msa.user.service.user.application.domain.User;
import com.example.msa.user.service.user.application.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * <b> 유저 영속성 어댑터 </b>
 */
@Component
@RequiredArgsConstructor
public class PersistenceAdapter {
	private final UserRepository userRepository;

	/**
	 * <b> 유저의 추가 정보 저장 </b>
	 * <p>
	 * - 임시로 이메일, 비밀번호, 역할, 나이로 지정
	 * </p>
	 */
	public void saveUser(User user) {
		// TODO - MSA는 처음이라 어떤 에러를 던져야할지 모르겠습니다. 추후 수정하겠습니다.
		// if (userRepository.existsById(user.getId())) {
		// 	throw new InternalException("이미 존재하는 사용자입니다.");
		// }
		userRepository.save(user);
	}
}
