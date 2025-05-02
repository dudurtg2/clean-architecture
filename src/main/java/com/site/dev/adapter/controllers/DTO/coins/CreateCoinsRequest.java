package com.site.dev.adapter.controllers.DTO.coins;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import com.site.dev.adapter.entity.UserEntity;
import com.site.dev.core.domain.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class  CreateCoinsRequest{
  String name;
  String symbol;
  String image;
  UserEntity user;
}
