package com.steem.lojavirtual.atualizacoes;

import com.steem.lojavirtual.jogo.Jogo;
import com.steem.lojavirtual.jogo.JogoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtualizacoesServices {

    private final AtualizacoesRepository repository;
    private final JogoRepository jogoRepository;

    public AtualizacoesServices(AtualizacoesRepository repository, JogoRepository jogoRepository) {
        this.repository = repository;
        this.jogoRepository = jogoRepository;
    }

    public Atualizcoes create(String descricao, Long jogoId) {
        Atualizcoes a = new Atualizcoes(descricao, LocalDateTime.now());
        if (jogoId != null) {
            Jogo jogo = jogoRepository.findById(jogoId).orElseThrow(() -> new RuntimeException("Jogo nao encontrado"));
            a.setJogo(jogo);
        }
        return repository.save(a);
    }

    public Atualizcoes edit(Long id, String descricao, Long jogoId) {
        Atualizcoes a = repository.findById(id).orElseThrow(() -> new RuntimeException("Atualizacao nao encontrada"));
        if (descricao != null) a.setDescricao(descricao);
        a.setData(LocalDateTime.now());
        return repository.save(a);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new RuntimeException("Atualizacao nao encontrada");
        repository.deleteById(id);
    }

    public Atualizcoes findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Atualizacao nao encontrada"));
    }

    public List<Atualizcoes> listAll() {
        return repository.findAll();
    }
}
