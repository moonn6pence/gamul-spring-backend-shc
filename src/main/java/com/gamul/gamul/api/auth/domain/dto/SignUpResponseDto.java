package com.gamul.gamul.api.auth.domain.dto;

import com.gamul.gamul.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {

    private String email;

    public static SignUpResponseDto of(Member member) {
        return new SignUpResponseDto(member.getEmail());
    }
}
