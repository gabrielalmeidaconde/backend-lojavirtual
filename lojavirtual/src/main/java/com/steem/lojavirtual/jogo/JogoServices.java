package com.steem.lojavirtual.jogo;

import com.steem.lojavirtual.empresa.Empresa;
import com.steem.lojavirtual.empresa.EmpresaRepository;
import com.steem.lojavirtual.genero.Genero;
import com.steem.lojavirtual.genero.GeneroRepository;
import com.steem.lojavirtual.jogo.dto.CreateJogoDTO;
import com.steem.lojavirtual.jogo.dto.EditJogoDTO;
import com.steem.lojavirtual.usuario.Usuario;
import com.steem.lojavirtual.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoServices {

    private final JogoRepository jogoRepository;
    private final GeneroRepository generoRepository;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public JogoServices(JogoRepository jogoRepository, GeneroRepository generoRepository, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.jogoRepository = jogoRepository;
        this.generoRepository = generoRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Jogo create(CreateJogoDTO dto) {
        List<Genero> generos = List.of();
        if (dto.generoIds() != null && !dto.generoIds().isEmpty()) {
            generos = generoRepository.findAllById(dto.generoIds());
            if (generos.size() != dto.generoIds().size()) {
                throw new RuntimeException("Um ou mais generos nao encontrados");
            }
        }

        Empresa empresa = empresaRepository.findById(dto.desenvolvedoraId())
                .orElseThrow(() -> new RuntimeException("Empresa nao encontrada"));

        Jogo jogo = new Jogo(dto.nome(), generos, dto.preco(), empresa);
        return jogoRepository.save(jogo);
    }

    public Jogo edit(EditJogoDTO dto) {
        Jogo jogo = jogoRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Jogo nao encontrado"));

        if (dto.nome() != null) jogo.setNome(dto.nome());
        if (dto.preco() != null) jogo.setPreco(dto.preco());
        if (dto.generoIds() != null) {
            List<Genero> generos = generoRepository.findAllById(dto.generoIds());
            if (generos.size() != dto.generoIds().size()) {
                throw new RuntimeException("Um ou mais generos nao encontrados");
            }
            jogo.setGeneros(generos);
        }
        if (dto.desenvolvedoraId() != null) {
            Empresa empresa = empresaRepository.findById(dto.desenvolvedoraId())
                    .orElseThrow(() -> new RuntimeException("Empresa nao encontrada"));
            jogo.setDesenvolvedora(empresa);
        }

        return jogoRepository.save(jogo);
    }

    public void delete(Long id) {
        if (!jogoRepository.existsById(id)) {
            throw new RuntimeException("Jogo nao encontrado");
        }
        jogoRepository.deleteById(id);
    }

    public Jogo comprar(Long jogoId, String usuarioemail) {
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new RuntimeException("Jogo nao encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioemail)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        jogo.comprarPor(usuario);
        return jogoRepository.save(jogo);
    }

    public Optional<Jogo> findById(Long id) {
        return jogoRepository.findById(id);
    }
}
