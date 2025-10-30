package com.steem.lojavirtual.usuario.dto;

import java.util.List;
import java.util.Objects;

public record CreateUsuarioDTO(
        String email,
        String password,
        String nome,
        List<String> roles
) {
    public CreateUsuarioDTO {
        email = Objects.toString(email, "");
        password = Objects.toString(password, "");
        nome = Objects.toString(nome, "");
        roles = Objects.requireNonNullElse(roles, List.of());
    }
}
