package com.site.dev.adapter.controllers.DTO.coins;

import com.site.dev.adapter.entity.UserEntity;

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
public class  CoinsRequest{
  String name;
  String symbol;
  String image;
  UserEntity user;
}
