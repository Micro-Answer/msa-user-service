package com.example.msa.user.service.user.adapter.out.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.msa.user.service.user.application.model.domain.User;

import lombok.RequiredArgsConstructor;

/**
 * <b> 유저 영속성 어댑터 </b>
 */
@Component
@RequiredArgsConstructor
public class PersistenceAdapter {
    private final Map<String, User> users = new HashMap<>();
    private int autoIncrement = 1;

    /**
     * <b> 유저의 추가 정보 저장 </b>
     * <p>
     * - 임시로 이메일, 비밀번호, 역할로 지정
     * </p>
     */
    public void saveUser(User user) {
        if (users.containsKey(user.getId())) {
            users.replace(user.getId(), user);
            return;
        }
        String id = getStringId();
        users.put(id, user);
        user.save(id);

        autoIncrement++;
    }

    /**
     * <b> id로 유저가 존재하는지 확인 </b>
     * @return 조회한 유저 아이디를 가지고 있는 유저
     */
    public User findById(String id) {
        return users.get(id);
    }

    /**
     * <b> userId로 유저가 존재하는지 확인 </b>
     * @return 유저 존재 여부
     */
    public boolean existsById(String userId) {
        return users.values().stream()
            .anyMatch(user -> user.getUserId().equals(userId));
    }

    private String getStringId() {
        return String.valueOf(autoIncrement);
    }
}
