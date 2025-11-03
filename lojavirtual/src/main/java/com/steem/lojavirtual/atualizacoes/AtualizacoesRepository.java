package com.steem.lojavirtual.atualizacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtualizacoesRepository extends JpaRepository<Atualizcoes, Long> {
    Optional<Atualizcoes> findTopByJogoIdOrderByIdDesc(Long jogoId);
}
