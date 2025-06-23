package com.site.dev.adapter.controllers.dto.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdatesUsers {
    private String name;
    private String email;
    private String password;
    private LocalDateTime dataNascimento;
    private String genero;
    private String cpf;
    private String telefone;
}
