package com.steem.lojavirtual.usuario;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository; // key = email

    private HashMap<String, Usuario> tokens = new HashMap<>();

    public Usuario cadastrarUsuario(Usuario usuario) {

        String password = usuario.getPassword();

        usuario.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        usuarioRepository.save( usuario);
        return usuario;
    }

    public Collection<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public String login(Usuario usuario) {

        Usuario user = usuarioRepository.findByEmail(usuario.getEmail());
        if (BCrypt.checkpw(usuario.getPassword(), user.getPassword())) {

            String token = UUID.randomUUID().toString();
            tokens.put(token, usuario);
            return token;
        }

        throw new RuntimeException("Usuário ou senha inválido");



    }

    public Usuario validarToken(String token) {

        Usuario usuario = tokens.get(token);

        if (usuario == null) {
            throw new RuntimeException("Token invalido");
        }

        return usuario;

    }
}
