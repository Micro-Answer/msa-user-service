package com.example.msa.user.service.user.adapter.out.db;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity {
    // MySQL를 가정하여 DB 식별자는 정수
    private int id;
    private String userId;
    private String pw;
    private String role;

    public UserEntity(int id, String userId, String pw, String role) {
        this.id = id;
        this.userId = userId;
        this.pw = pw;
        this.role = role;
    }
}
