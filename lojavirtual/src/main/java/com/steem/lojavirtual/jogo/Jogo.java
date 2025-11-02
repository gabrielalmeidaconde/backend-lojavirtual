package com.steem.lojavirtual.jogo;

import com.steem.lojavirtual.atualizacoes.Atualizcoes;
import com.steem.lojavirtual.empresa.Empresa;
import com.steem.lojavirtual.genero.Genero;
import com.steem.lojavirtual.usuario.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(name = "jogo_genero",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"jogo_id", "genero_id"}))
    private List<Genero> generos = new ArrayList<>();

    private Double preco;
    private String imagemUrl; // URL ou caminho da imagem JPG
    
    @ManyToOne
    private Empresa desenvolvedora;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atualizcoes> atualizacoes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "jogo_usuario",
            joinColumns = @JoinColumn(name = "jogo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_email"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"jogo_id", "usuario_email"}))
    private List<Usuario> usuarios = new ArrayList<>();

    public Jogo() {
    }

    public Jogo(String nome, List<Genero> generos, Double preco, Empresa desenvolvedora) {
        this.nome = nome;
        this.generos = generos != null ? generos : new ArrayList<>();
        this.preco = preco;
        this.desenvolvedora = desenvolvedora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public Double getPreco() {
        return preco;
    }

    public Empresa getDesenvolvedora() {
        return desenvolvedora;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public List<Atualizcoes> getAtualizacoes() {
        return atualizacoes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public void addGenero(Genero genero) {
        if (genero == null) return;
        if (!this.generos.contains(genero)) this.generos.add(genero);
    }

    public void removeGenero(Genero genero) {
        if (genero == null) return;
        this.generos.remove(genero);
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setDesenvolvedora(Empresa desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void adicionarAtualizacao(Atualizcoes atualizacao) {
        if (atualizacao == null) return;
        atualizacao.setJogo(this);
        this.atualizacoes.add(atualizacao);
    }

    public void removerAtualizacao(Atualizcoes atualizacao) {
        if (atualizacao == null) return;
        atualizacao.setJogo(null);
        this.atualizacoes.remove(atualizacao);
    }

    public void comprarPor(Usuario usuario) {
        if (usuario == null) return;
        if (!this.usuarios.contains(usuario)) {
            this.usuarios.add(usuario);
        }
    }
}
