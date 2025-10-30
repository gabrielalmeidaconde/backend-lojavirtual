package com.steem.lojavirtual.usuario.dto;

import java.util.List;
import java.util.Objects;

public record ResponseUsuarioDTO(
        String email,
        String nome,
        List<String> roles
) {
    public ResponseUsuarioDTO {
        email = Objects.toString(email, "");
        nome = Objects.toString(nome, "");
        roles = Objects.requireNonNullElse(roles, List.of());
    }
}
