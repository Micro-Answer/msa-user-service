package com.example.msa.user.service.user.adapter.out.db;

import java.util.HashMap;
import java.util.Map;

import com.example.msa.user.service.user.domain.model.User;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

/**
 * <b> 유저 영속성 어댑터 </b>
 */
@Component
@RequiredArgsConstructor
public class PersistenceAdapter {
    private final Map<String, UserEntity> users = new HashMap<>();
    private int autoIncrement = 1;

    /**
     * <b> 유저에 대한 최소한의 정보 저장 </b>
     * <p>
     * - 동시성을 고려하여 중복 아이디 가입 방지
     * </p>
     * @param userId 사용자가 회원 가입 시 등록한 문자열 아이디
     * @param pw 비밀번호
     * @param role 역할
     */
    public synchronized boolean create(String userId, String pw, String role) {
        if (users.containsKey(userId)) {
            return false;
        }

        users.put(userId, new UserEntity(autoIncrement, userId, pw, role));
        autoIncrement++;
        return true;
    }

    /**
     * <b> 데이터베이스 식별자로 유저 조회 </b>
     * <p>
     * - MySQL의 경우, 정수로 변환
     * - MongoDB의 경우, 문자열 그대로 사용
     * - 현재는 MySQL의 상황을 가정하여 정수로 변환
     * </p>
     * @param id 영속성 어댑터 식별자
     * @return 데이터베이스 식별자에 해당하는 유저
     */
    public User findById(String id) {
        int mysqlId = Integer.parseInt(id);
        for (UserEntity entity : users.values()) {
            if (entity.getId() == mysqlId) {
                return toUser(entity);
            }
        }
        return null;
    }

    /**
     * <b> 유저 아이디로 유저 조회 </b>
     * @param userId 사용자가 회원가입 시 등록한 아이디
     * @return 유저 아이디를 가지고 있는 유저
     */
    public User findByUserId(String userId) {
        UserEntity entity = users.get(userId);
        return entity != null ? toUser(entity) : null;
    }

    /**
     * <b> 영속성 엔티티를 도메인 모델로 매핑 </b>
     * <p>
     * - 어댑터 밖으로 보내는 데이터베이스 식별자는 문자열로 변환
     * </p>
     * @param entity 영속성 엔티티
     * @return 도메인 모델
     */
    private User toUser(UserEntity entity) {
        return new User(
                String.valueOf(entity.getId()), // 어댑터 밖으로 보내는 데이터베이스 식별자는 문자열로 변환
                entity.getUserId(),
                entity.getPw(),
                entity.getRole()
        );
    }

    /**
     * <b> userId로 유저가 존재하는지 확인 </b>
     * @param userId 사용자가 등록한 아이디
     * @return 유저 존재 여부
     */
    public boolean existsByUserId(String userId) {
        return users.values().stream()
                .anyMatch(user -> user.getUserId().equals(userId));
    }
}
