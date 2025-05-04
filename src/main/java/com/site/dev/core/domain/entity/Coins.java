package com.site.dev.core.domain.entity;


import lombok.Builder;
import lombok.Data;
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
public class Coins {
    private Long id;
    private String name;
    private String symbol;
    private String image;
    private Users user;
}
