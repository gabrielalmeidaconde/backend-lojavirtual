package com.steem.lojavirtual.jogo.dto;

import java.util.List;

public class ResponseJogoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private List<String> generos;
    private String desenvolvedora;
    private int usuariosCount;
    private String imagemUrl;

    public ResponseJogoDTO(Long id, String nome, Double preco, List<String> generos, String desenvolvedora, int usuariosCount, String imagemUrl) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.generos = generos;
        this.desenvolvedora = desenvolvedora;
        this.usuariosCount = usuariosCount;
        this.imagemUrl = imagemUrl;
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

    public String getImagemUrl() {
        return imagemUrl;
    }
}
