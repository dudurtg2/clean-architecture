package com.site.dev.adapter.controllers.DTO.coins;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateCoinsResponse {
    private String name;
    private String symbol;
    private String image;
    private Float totalValue;
    private Float inputValue;
    private Float outputValue;
}
