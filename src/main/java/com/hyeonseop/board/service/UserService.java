package com.hyeonseop.board.service;

import com.hyeonseop.board.Repository.UserRepository;
import com.hyeonseop.board.dto.PatchUserDto;
import com.hyeonseop.board.dto.PatchUserResponseDto;
import com.hyeonseop.board.dto.ResponseDto;
import com.hyeonseop.board.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseDto<PatchUserResponseDto> patchUser(PatchUserDto dto, String userEmail) {

        UserEntity userEntity = null;
        String userNickname = dto.getUserNickname();
        String userProfile = dto.getUserProfile();

        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) return ResponseDto.setFailed("Does Not Exist User");

            userEntity.setUserNickname(userNickname);
            userEntity.setUserProfile(userProfile);

            userRepository.save(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        userEntity.setUserPassword("");

        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);

        return ResponseDto.setSuccess("Success", patchUserResponseDto);
    }
}
