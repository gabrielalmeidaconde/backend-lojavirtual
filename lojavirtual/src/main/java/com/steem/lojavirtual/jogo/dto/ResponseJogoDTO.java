package com.steem.lojavirtual.jogo.dto;

import java.util.List;

public class ResponseJogoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private List<String> generos;
    private String desenvolvedora;
    private int usuariosCount;

    public ResponseJogoDTO(Long id, String nome, Double preco, List<String> generos, String desenvolvedora, int usuariosCount) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.generos = generos;
        this.desenvolvedora = desenvolvedora;
        this.usuariosCount = usuariosCount;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public int getUsuariosCount() {
        return usuariosCount;
    }
}
