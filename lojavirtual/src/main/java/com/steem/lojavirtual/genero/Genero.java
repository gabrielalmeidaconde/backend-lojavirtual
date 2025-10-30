package com.steem.lojavirtual.genero;

import com.steem.lojavirtual.jogo.Jogo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(mappedBy = "generos")
    private List<Jogo> jogos = new ArrayList<>();

    public Genero() {
    }

    public Genero(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public void addJogo(Jogo jogo) {
        if (jogo == null) return;
        if (!this.jogos.contains(jogo)) this.jogos.add(jogo);
    }

    public void removeJogo(Jogo jogo) {
        if (jogo == null) return;
        this.jogos.remove(jogo);
    }
}
