package com.steem.lojavirtual.usuario.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public record RelatorioUsuarioDTO(
        String email,
        String nome,
        int totalLogins,
        int activeTokens,
        LocalDateTime lastLogin
) {
    public RelatorioUsuarioDTO {
        email = Objects.toString(email, "");
        nome = Objects.toString(nome, "");
    }
}
