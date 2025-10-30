package com.steem.lojavirtual.empresa;

import com.steem.lojavirtual.jogo.Jogo;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany
    ArrayList<Jogo> jogos= new ArrayList<>();

    public Empresa() {
    }

    public Empresa(String nome) {
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
    public void adicionarJogo(Jogo jogo){
        this.jogos.add(jogo);
    }
}
