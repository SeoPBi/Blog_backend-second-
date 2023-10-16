package com.hyeonseop.board.Repository;

import com.hyeonseop.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
