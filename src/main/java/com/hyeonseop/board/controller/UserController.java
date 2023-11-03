package com.hyeonseop.board.controller;

import com.hyeonseop.board.dto.PatchUserDto;
import com.hyeonseop.board.dto.PatchUserResponseDto;
import com.hyeonseop.board.dto.ResponseDto;
import com.hyeonseop.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PatchMapping("/")
    public ResponseDto<PatchUserResponseDto> patchUser(@RequestBody PatchUserDto requestBody, @AuthenticationPrincipal String userEmail) {
        return null;
    }
}
