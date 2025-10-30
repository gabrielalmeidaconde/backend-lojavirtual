package com.steem.lojavirtual.empresa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public Empresa create(String nome) {
        Empresa e = new Empresa(nome);
        return repository.save(e);
    }

    public Empresa edit(Long id, String nome) {
        Empresa e = repository.findById(id).orElseThrow(() -> new RuntimeException("Empresa nao encontrada"));
        if (nome != null) e.setNome(nome);
        return repository.save(e);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Empresa nao encontrada");
        repository.deleteById(id);
    }

    public Empresa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Empresa nao encontrada"));
    }

    public List<Empresa> listAll() {
        return repository.findAll();
    }
}
