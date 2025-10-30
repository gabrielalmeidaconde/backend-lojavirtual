package com.steem.lojavirtual.jogo.dto;

import java.util.List;

public record EditJogoDTO(Long id, String nome, List<Long> generoIds, Double preco, Long desenvolvedoraId) {
}
