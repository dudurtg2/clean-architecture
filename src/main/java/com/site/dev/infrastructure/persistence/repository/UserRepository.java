package com.site.dev.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.infrastructure.persistence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
