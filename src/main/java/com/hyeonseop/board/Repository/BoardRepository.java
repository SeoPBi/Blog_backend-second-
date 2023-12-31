package com.hyeonseop.board.Repository;

import com.hyeonseop.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    public List<BoardEntity> findTop3ByBoardWriteDateAfterOrderByBoardLikesCountDesc(Date boardWriteDate);

    public List<BoardEntity> findByOrderByBoardWriteDateDesc();

    public List<BoardEntity> findByBoardTitleContains(String boardTile);
}
