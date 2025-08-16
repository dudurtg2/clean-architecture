package com.site.dev.core.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.site.dev.core.domain.enums.UserRole;
import com.site.dev.core.domain.exception.IncorrectBodyException;
import com.site.dev.core.domain.exception.WeakPasswordException;

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
public class Users {
    private UUID uuid;
    private String name;
    private String email;
    private String sub;
    private String password;
    private UserRole role;
    private LocalDate dataNascimento;
    private String genero;
    private String cpf;
    private String telefone;
    private String apiKey;

    public Users correct() {

        if (name == null || name.isBlank()) {
            throw new IncorrectBodyException();
        }

        if (email == null || email.isBlank()
                || !email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new IncorrectBodyException();
        }

        if (password == null || password.length() < 8) {
            throw new WeakPasswordException();
        }

        if (role == null) {
            throw new IncorrectBodyException();
        }

        return this;
    }

    
}
