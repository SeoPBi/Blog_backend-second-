package com.hyeonseop.board.dto;

import com.hyeonseop.board.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserResponseDto {

    private UserEntity user;

}
