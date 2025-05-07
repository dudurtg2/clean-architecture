package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;

import org.mapstruct.control.MappingControl;

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
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime dataNascimento;
    private String genero;

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

        if (dataNascimento == null
                || dataNascimento.isAfter(LocalDateTime.now())) {
            throw new IncorrectBodyException();
        }

        if (genero == null || genero.isBlank()) {
            throw new IncorrectBodyException();
        }

        return this;
    }
}
