package com.steem.lojavirtual.genero.dto;

public class ResponseGeneroDTO {
    private final Long id;
    private final String nome;

    public ResponseGeneroDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

