package com.site.dev.adapter.models;

import com.site.dev.core.domain.entity.Coins;
import com.site.dev.core.domain.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "goals")
public class GoalsEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "goal")
    private float goal;

    @Column(name = "description")
    private String description;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "coins_uuid")
    private CoinsEntity coins;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UsersEntity user;

}
