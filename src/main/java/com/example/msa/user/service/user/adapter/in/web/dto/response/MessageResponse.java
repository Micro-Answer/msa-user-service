package com.example.msa.user.service.user.adapter.in.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b> 역할: 유저 회원가입 응답 </b>
 * <p>
 * - HTTP 응답 바디에 매핑
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
    private String result;

    public MessageResponse(String result) {
        this.result = result;
    }
}
