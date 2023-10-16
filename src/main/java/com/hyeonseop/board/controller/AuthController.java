package com.hyeonseop.board.controller;

import com.hyeonseop.board.DTO.ResponseDto;
import com.hyeonseop.board.DTO.SignInDto;
import com.hyeonseop.board.DTO.SignInResponseDto;
import com.hyeonseop.board.DTO.SignUpDto;
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
