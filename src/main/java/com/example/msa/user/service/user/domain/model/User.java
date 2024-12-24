package com.example.msa.user.service.user.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String id; // MySQL, MongoDB 식별자 타입에 무관한 문자열 DB 식별자
    private String userId; // 사용자가 작성한 유저 아이디
    private String pw;
    private String role;

    public User(String id, String userId, String pw, String role) {
        this.id = id;
        this.userId = userId;
        this.pw = pw;
        this.role = role;
    }
}
