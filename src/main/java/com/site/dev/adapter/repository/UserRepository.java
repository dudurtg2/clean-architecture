package com.site.dev.adapter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.site.dev.adapter.models.UsersEntity;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    List<UsersEntity> findAll();
    UsersEntity findByEmail(String email);
  
}
