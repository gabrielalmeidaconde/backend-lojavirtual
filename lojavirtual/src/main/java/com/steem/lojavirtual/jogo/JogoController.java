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
        return new ResponseEntity<>(jogoServices.converterParaDTO(jogo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseJogoDTO> edit(@RequestBody EditJogoDTO dto) {
        Jogo jogo = jogoServices.edit(dto);
        return ResponseEntity.ok(jogoServices.converterParaDTO(jogo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jogoServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comprar")
    public ResponseEntity<ResponseJogoDTO> comprar(@PathVariable Long id, @RequestParam String usuarioemail) {
        Jogo jogo = jogoServices.comprar(id, usuarioemail);
        return ResponseEntity.ok(jogoServices.converterParaDTO(jogo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJogoDTO> getById(@PathVariable Long id) {
        return jogoServices.findById(id)
                .map(jogo -> ResponseEntity.ok(jogoServices.converterParaDTO(jogo)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<ResponseJogoDTO>> listAll() {
        List<Jogo> jogos = jogoServices.listAll();
        List<ResponseJogoDTO> responseDTOs = jogos.stream()
                .map(jogoServices::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Lista os jogos comprados por um usuário ("Meus Pedidos")
    @GetMapping("/comprados")
    public ResponseEntity<List<ResponseJogoDTO>> listComprados(@RequestParam String usuarioemail) {
        System.out.println("🔍 [JogoController] GET /jogos/comprados - usuarioemail: " + usuarioemail);
        try {
            List<Jogo> jogos = jogoServices.listByUsuarioEmail(usuarioemail);
            System.out.println("✅ [JogoController] Encontrados " + jogos.size() + " jogos comprados");
            List<ResponseJogoDTO> responseDTOs = jogos.stream()
                    .map(jogoServices::converterParaDTO)
                    .collect(Collectors.toList());
            System.out.println("✅ [JogoController] Retornando " + responseDTOs.size() + " DTOs");
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            System.err.println("❌ [JogoController] Erro ao buscar jogos comprados: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // Verifica se um usuário possui um jogo
    @GetMapping("/{id}/possui")
    public ResponseEntity<Boolean> usuarioPossuiJogo(@PathVariable Long id, @RequestParam String usuarioemail) {
        boolean possui = jogoServices.usuarioPossuiJogo(id, usuarioemail);
        return ResponseEntity.ok(possui);
    }
}
