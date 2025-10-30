package com.steem.lojavirtual.atualizacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtualizacoesRepository extends JpaRepository<Atualizcoes, Long> {
}
