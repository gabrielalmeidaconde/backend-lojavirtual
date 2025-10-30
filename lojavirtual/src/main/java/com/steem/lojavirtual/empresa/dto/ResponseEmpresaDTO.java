package com.steem.lojavirtual.empresa.dto;

public class ResponseEmpresaDTO {
    private final Long id;
    private final String nome;

    public ResponseEmpresaDTO(Long id, String nome) {
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

