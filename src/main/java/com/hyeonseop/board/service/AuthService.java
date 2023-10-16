package com.hyeonseop.board.service;

import com.hyeonseop.board.DTO.ResponseDto;
import com.hyeonseop.board.DTO.SignInDto;
import com.hyeonseop.board.DTO.SignInResponseDto;
import com.hyeonseop.board.DTO.SignUpDto;
import com.hyeonseop.board.Repository.UserRepository;
import com.hyeonseop.board.Secure.TokenProvider;
import com.hyeonseop.board.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    public ResponseDto<?> signUp(SignUpDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPpasswordCheck = dto.getUserPasswordCheck();

        // email 중복 확인
        try {
            if(userRepository.existsById(userEmail))
                return ResponseDto.setFailed("Existed Email!");
        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error!");
        }

        // 비밀번호가 서로 다르면 failed response 반환!
        if(!userPassword.equals(userPpasswordCheck))
            return ResponseDto.setFailed("Password does not matched!");

        // UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);

        // UserRepository를 이용해서 데이터베이스 Entity 저장!!
        try {
            userRepository.save(userEntity);
        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error!");
        }

        // 성공시 Success response 반환
        return ResponseDto.setSuccess("SignUp Success!", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        try {
            boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
            if (!existed) return ResponseDto.setFailed("Sign In Information does not Match");
        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error");
        }
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findById(userEmail).get();
        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setUserPassword("");

        String token = tokenProvider.create(userEmail);
        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity);
        return ResponseDto.setSuccess("Sign in Success", signInResponseDto);
    }
}
