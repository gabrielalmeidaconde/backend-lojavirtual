package com.steem.lojavirtual.atualizacoes.dto;

import java.time.LocalDateTime;

public class ResponseAtualizacaoDTO {
    private final Long id;
    private final String descricao;
    private final LocalDateTime data;
    private final Long jogoId;
    private final String jogoNome;

    public ResponseAtualizacaoDTO(Long id, String descricao, LocalDateTime data, Long jogoId, String jogoNome) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.jogoId = jogoId;
        this.jogoNome = jogoNome;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Long getJogoId() {
        return jogoId;
    }

    public String getJogoNome() {
        return jogoNome;
    }
}
