package com.steem.lojavirtual.jogo.dto;

import java.util.List;

public class ResponseJogoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Integer desconto;
    private List<String> generos;
    private String desenvolvedora;
    private int usuariosCount;
    private String imagemUrl;
    private String descricao;
    private String ultimaVersao;

    public ResponseJogoDTO(Long id, String nome, Double preco, Integer desconto, List<String> generos, String desenvolvedora, int usuariosCount, String imagemUrl, String descricao, String ultimaVersao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.desconto = desconto;
        this.generos = generos;
        this.desenvolvedora = desenvolvedora;
        this.usuariosCount = usuariosCount;
        this.imagemUrl = imagemUrl;
        this.descricao = descricao;
        this.ultimaVersao = ultimaVersao;
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

    public Integer getDesconto() {
        return desconto != null ? desconto : 0;
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

    public String getDescricao() {
        return descricao;
    }

    public String getUltimaVersao() {
        return ultimaVersao;
    }
}
