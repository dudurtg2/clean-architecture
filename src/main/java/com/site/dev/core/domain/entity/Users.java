package com.site.dev.core.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    private UserRole role;
    private LocalDateTime dataNascimento;
    private String genero;
    private String cpf;
    private String telefone;

    public Users correct() {
        if (cpf != null) {
            if (!validCPF()) {
                throw new IncorrectBodyException();
            }

        }
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

    
    private  boolean validCPF() {
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;
        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            d1 = d1 + (11 - nCount) * digitoCPF;

            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        resto = (d1 % 11);

        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d1 = 0;

        for (int nCount = 0; nCount < 10; nCount++) {
            d1 = d1 + nCount * Integer.valueOf(cpf.substring(nCount, nCount + 1)).intValue();
        }

        resto = (d1 % 11);

        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }
}
