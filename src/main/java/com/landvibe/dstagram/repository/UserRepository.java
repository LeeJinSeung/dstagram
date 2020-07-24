package com.landvibe.dstagram.repository;

import com.landvibe.dstagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByNickname(String nickname);
    User deleteByEmail(String eamil);
}
