package com.steem.lojavirtual.genero;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServices {

    private final GeneroRepository repository;

    public GeneroServices(GeneroRepository repository) {
        this.repository = repository;
    }

    public Genero create(String nome) {
        Genero g = new Genero(nome);
        return repository.save(g);
    }

    public Genero edit(Long id, String nome) {
        Genero g = repository.findById(id).orElseThrow(() -> new RuntimeException("Genero nao encontrado"));
        if (nome != null) g.setNome(nome);
        return repository.save(g);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Genero nao encontrado");
        repository.deleteById(id);
    }

    public Genero findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Genero nao encontrado"));
    }

    public List<Genero> listAll() {
        return repository.findAll();
    }
}
