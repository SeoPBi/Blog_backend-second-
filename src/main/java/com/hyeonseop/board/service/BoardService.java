package com.hyeonseop.board.service;

import com.hyeonseop.board.Repository.BoardRepository;
import com.hyeonseop.board.Repository.PopularSearchRepository;
import com.hyeonseop.board.dto.ResponseDto;
import com.hyeonseop.board.entity.BoardEntity;
import com.hyeonseop.board.entity.PopularSearchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    PopularSearchRepository popularSearchRepository;

    public ResponseDto<List<BoardEntity>> getTop3() {
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));

        try {
            boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikesCountDesc(date);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<BoardEntity>> getList() {
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try {
            boardList = boardRepository.findByOrderByBoardWriteDateDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }

        return ResponseDto.setSuccess("Success", boardList);
    }

    public ResponseDto<List<PopularSearchEntity>> getPopularsearchList() {

        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();

        try {
            popularSearchList = popularSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("Database Error");
        }
        return ResponseDto.setSuccess("Success", popularSearchList);
    }
}
