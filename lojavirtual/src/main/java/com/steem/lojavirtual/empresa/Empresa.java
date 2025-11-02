package com.steem.lojavirtual.empresa;

import com.steem.lojavirtual.jogo.Jogo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List; // ← Adicionar este import

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    @OneToMany(mappedBy = "desenvolvedora") // ← Adicionar mappedBy
    private List<Jogo> jogos = new ArrayList<>(); // ← Mudar de ArrayList para List

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