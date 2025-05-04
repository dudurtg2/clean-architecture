package com.site.dev.adapter.entity;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.TypeCoins;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "movements")

public class MovementsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "value")
    private Float value;

    @Column(name = "price")
    private Float price;

    @Column(name = "type_coins")
    private TypeCoins typeCoins;

    @ManyToOne
    @JoinColumn(name = "coins_id")
    private CoinsEntity coins;
    
}
