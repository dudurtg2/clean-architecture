package com.site.dev.adapter.controllers.dto.coins;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class  CoinsRequest{
  private UUID uuid;
  private String name;
    private String symbol;
    private String category;
    private String description;
    private String subcategory;
    private String color;
    private String image;
}
