package com.site.dev.adapter.controllers.dto.users;

import java.time.LocalDateTime;

import com.site.dev.core.domain.enums.UserRole;

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
public class UsersRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime dataNascimento;
    private String genero;

}

