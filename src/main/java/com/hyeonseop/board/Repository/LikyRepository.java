package com.hyeonseop.board.Repository;

import com.hyeonseop.board.entity.LikyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, Integer> {
}
