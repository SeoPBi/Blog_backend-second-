package com.hyeonseop.board.service;

import com.hyeonseop.board.dto.ResponseDto;
import com.hyeonseop.board.dto.SignInDto;
import com.hyeonseop.board.dto.SignInResponseDto;
import com.hyeonseop.board.dto.SignUpDto;
import com.hyeonseop.board.Repository.UserRepository;
import com.hyeonseop.board.Secure.TokenProvider;
import com.hyeonseop.board.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        // 비밀번호 암호화
        String encodedpaasword = passwordEncoder.encode(userPassword);
        userEntity.setUserPassword(encodedpaasword);

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

        UserEntity userEntity = null ;
        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            // 잘못된 이메일
            if (userEntity == null ) return ResponseDto.setFailed("Sign in Failed");
            // 잘못된 패스워드
            if (!passwordEncoder.matches(userPassword, userEntity.getUserPassword()))
                return ResponseDto.setFailed("Sign in Failed");
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

