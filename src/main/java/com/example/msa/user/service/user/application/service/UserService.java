package com.example.msa.user.service.user.application.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.msa.user.service.user.adapter.out.db.PersistenceAdapter;
import com.example.msa.user.service.user.domain.model.User;

import lombok.RequiredArgsConstructor;

/**
 * <b> 유저 서비스 </b>
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final PersistenceAdapter persistenceAdapter;

    /**
     * <b> 유저 회원가입 로직 수행 </b>
     *
     * @param userId 사용자가 작성한 아이디
     * @param pw 비밀번호
     * @param role 역할
     * @return 회원가입 성공 여부
     */
    public boolean signUp(String userId, String pw, String role) {
        return persistenceAdapter.create(userId, pw, role);
    }

    /**
     * <b> 유저 로그인 로직 수행 </b>
     *
     * @param userId 회원가입 시 아이디
     * @param pw 비밀번호
     * @return 로그인 성공 여부
     */
    public boolean signIn(String userId, String pw) {
        User user = persistenceAdapter.findByUserId(userId);
         if (user != null && user.getPw().equals(pw)) {
             return true;
         }

        logger.log(Level.WARNING, "로그인 실패 : 잘못된 ID 또는 비밀번호");
        return false;
    }
}
