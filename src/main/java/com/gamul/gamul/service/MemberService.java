package com.gamul.gamul.service;

import com.gamul.gamul.api.auth.domain.dto.SignUpResponseDto;
import com.gamul.gamul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public SignUpResponseDto getMemberInfo(String email) {
        return memberRepository.findByEmail(email)
                .map(SignUpResponseDto::of)
                .orElseThrow(()->new RuntimeException("유저 정보가 없습니다."));
    }
}
