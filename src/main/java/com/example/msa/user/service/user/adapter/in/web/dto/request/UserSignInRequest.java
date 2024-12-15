package com.example.msa.user.service.user.adapter.in.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInRequest {
    private String id;
    private String pw;

    public UserSignInRequest(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
