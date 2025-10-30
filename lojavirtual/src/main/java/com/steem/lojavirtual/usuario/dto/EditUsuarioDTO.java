package com.steem.lojavirtual.usuario.dto;

import java.util.List;
import java.util.Objects;

public record EditUsuarioDTO(
        String nome,
        String password,
        List<String> roles
) {
    public EditUsuarioDTO {
        nome = Objects.toString(nome, "");
        password = Objects.toString(password, "");
        roles = Objects.requireNonNullElse(roles, List.of());
    }
}
