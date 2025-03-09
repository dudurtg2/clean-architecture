package com.site.dev.adapter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.adapter.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    UserEntity findByEmail(String email);
}
