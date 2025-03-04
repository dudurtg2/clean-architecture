package com.site.dev.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.infrastructure.persistence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
}
