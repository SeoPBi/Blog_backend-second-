package com.hyeonseop.board.controller;

import com.hyeonseop.board.dto.ResponseDto;
import com.hyeonseop.board.dto.SignInDto;
import com.hyeonseop.board.dto.SignInResponseDto;
import com.hyeonseop.board.dto.SignUpDto;
import com.hyeonseop.board.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signUp")
    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody) {
        ResponseDto<?> result = authService.signUp(requestBody);
        return result;
    }

    @PostMapping("/signIn")
    public ResponseDto<SignInResponseDto> signIn(@RequestBody SignInDto requestBody) {
        ResponseDto<SignInResponseDto> result = authService.signIn(requestBody);
        return result;
    }
}
