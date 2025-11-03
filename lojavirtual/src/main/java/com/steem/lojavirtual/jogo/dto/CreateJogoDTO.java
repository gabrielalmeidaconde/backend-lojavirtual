package com.steem.lojavirtual.jogo.dto;

import java.util.List;

public record CreateJogoDTO(String nome, List<Long> generoIds, Double preco, Integer desconto, Long desenvolvedoraId, String imagemUrl, String descricao) {
}
