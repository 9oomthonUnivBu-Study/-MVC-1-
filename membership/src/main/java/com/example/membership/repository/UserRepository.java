package com.example.membership.repository;

import com.example.membership.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserId(String userId);
}
