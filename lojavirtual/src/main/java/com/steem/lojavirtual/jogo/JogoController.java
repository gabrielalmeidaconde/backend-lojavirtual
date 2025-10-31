package com.steem.lojavirtual.jogo;

import com.steem.lojavirtual.jogo.dto.CreateJogoDTO;
import com.steem.lojavirtual.jogo.dto.EditJogoDTO;
import com.steem.lojavirtual.jogo.dto.ResponseJogoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoServices jogoServices;

    public JogoController(JogoServices jogoServices) {
        this.jogoServices = jogoServices;
    }

    @PostMapping
    public ResponseEntity<ResponseJogoDTO> create(@RequestBody CreateJogoDTO dto) {
        Jogo jogo = jogoServices.create(dto);
        List<String> generoNomes = jogo.getGeneros().stream().map(g -> g.getNome()).collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseJogoDTO(jogo.getId(), jogo.getNome(), jogo.getPreco(), generoNomes, jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora().getNome() : null, jogo.getUsuarios().size()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseJogoDTO> edit(@RequestBody EditJogoDTO dto) {
        Jogo jogo = jogoServices.edit(dto);
        List<String> generoNomes = jogo.getGeneros().stream().map(g -> g.getNome()).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseJogoDTO(jogo.getId(), jogo.getNome(), jogo.getPreco(), generoNomes, jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora().getNome() : null, jogo.getUsuarios().size()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jogoServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comprar")
    public ResponseEntity<ResponseJogoDTO> comprar(@PathVariable Long id, @RequestParam String usuarioemail) {
        Jogo jogo = jogoServices.comprar(id, usuarioemail);
        List<String> generoNomes = jogo.getGeneros().stream().map(g -> g.getNome()).collect(Collectors.toList());
        return ResponseEntity.ok(new ResponseJogoDTO(jogo.getId(), jogo.getNome(), jogo.getPreco(), generoNomes, jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora().getNome() : null, jogo.getUsuarios().size()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJogoDTO> getById(@PathVariable Long id) {
        return jogoServices.findById(id)
                .map(jogo -> {
                    List<String> generoNomes = jogo.getGeneros().stream().map(g -> g.getNome()).collect(Collectors.toList());
                    return ResponseEntity.ok(new ResponseJogoDTO(jogo.getId(), jogo.getNome(), jogo.getPreco(), generoNomes, jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora().getNome() : null, jogo.getUsuarios().size()));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<ResponseJogoDTO>> listAll() {
        List<Jogo> jogos = jogoServices.listAll();
        List<ResponseJogoDTO> responseDTOs = jogos.stream().map(jogo -> {
            List<String> generoNomes = jogo.getGeneros().stream().map(g -> g.getNome()).collect(Collectors.toList());
            return new ResponseJogoDTO(jogo.getId(), jogo.getNome(), jogo.getPreco(), generoNomes, jogo.getDesenvolvedora() != null ? jogo.getDesenvolvedora().getNome() : null, jogo.getUsuarios().size());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }
}
