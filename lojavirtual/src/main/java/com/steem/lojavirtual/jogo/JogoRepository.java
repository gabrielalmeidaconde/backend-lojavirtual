package com.steem.lojavirtual.jogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
	// Lista todos os jogos comprados por um usuário (associação ManyToMany jogo_usuario)
	// Observação: para navegar pela propriedade aninhada (usuarios.email) em Spring Data, use o sublinhado
	List<Jogo> findByUsuarios_Email(String email);

	// Query com fetch apenas de generos e desenvolvedora (não fetch usuarios para evitar MultipleBagFetchException)
	@Query("select distinct j from Jogo j\n" +
		   " join j.usuarios u\n" +
		   " left join fetch j.generos\n" +
		   " left join fetch j.desenvolvedora\n" +
		   " where u.email = :email")
	List<Jogo> findCompradosComFetchByUsuarioEmail(@Param("email") String email);
}