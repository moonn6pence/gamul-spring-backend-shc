package com.gamul.gamul.api.auth.controller;

import com.gamul.gamul.api.auth.service.AuthService;
import com.gamul.gamul.api.web.DefaultResponse;
import com.gamul.gamul.api.web.ResponseMessage;
import com.gamul.gamul.api.web.StatusCode;
import com.gamul.gamul.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.CREATED, true, ResponseMessage.CREATED_USER, authService.signup(memberRequestDto)), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.OK, true, ResponseMessage.LOGIN_SUCCESS, authService.login(loginRequestDto)), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity(DefaultResponse.res(StatusCode.OK, true, ResponseMessage.REISSUE, authService.reissue(tokenRequestDto)), HttpStatus.OK);
    }

}
