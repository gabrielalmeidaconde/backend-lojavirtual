package com.steem.lojavirtual.atualizacoes;

import com.steem.lojavirtual.atualizacoes.dto.CreateAtualizacaoDTO;
import com.steem.lojavirtual.atualizacoes.dto.ResponseAtualizacaoDTO;
import com.steem.lojavirtual.atualizacoes.dto.EditAtualizacaoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atualizacoes")
public class AtualizacoesController {

    private final AtualizacoesServices service;

    public AtualizacoesController(AtualizacoesServices service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseAtualizacaoDTO> create(@RequestBody CreateAtualizacaoDTO dto) {
        Atualizcoes a = service.create(dto.descricao(), dto.jogoId(), dto.versao());
        Long jogoId = a.getJogo() != null ? a.getJogo().getId() : null;
        String jogoNome = a.getJogo() != null ? a.getJogo().getNome() : null;
        return new ResponseEntity<>(new ResponseAtualizacaoDTO(a.getId(), a.getDescricao(), a.getData(), jogoId, jogoNome), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseAtualizacaoDTO> edit(@RequestBody EditAtualizacaoDTO dto) {
        Atualizcoes a = service.edit(dto.id(), dto.descricao(), dto.jogoId());
        Long jogoId = a.getJogo() != null ? a.getJogo().getId() : null;
        String jogoNome = a.getJogo() != null ? a.getJogo().getNome() : null;
        return ResponseEntity.ok(new ResponseAtualizacaoDTO(a.getId(), a.getDescricao(), a.getData(), jogoId, jogoNome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAtualizacaoDTO> getById(@PathVariable Long id) {
        Atualizcoes a = service.findById(id);
        Long jogoId = a.getJogo() != null ? a.getJogo().getId() : null;
        String jogoNome = a.getJogo() != null ? a.getJogo().getNome() : null;
        return ResponseEntity.ok(new ResponseAtualizacaoDTO(a.getId(), a.getDescricao(), a.getData(), jogoId, jogoNome));
    }

    @GetMapping
    public ResponseEntity<List<ResponseAtualizacaoDTO>> listAll() {
        return ResponseEntity.ok(service.listAll().stream().map(a -> {
            Long jogoId = a.getJogo() != null ? a.getJogo().getId() : null;
            String jogoNome = a.getJogo() != null ? a.getJogo().getNome() : null;
            return new ResponseAtualizacaoDTO(a.getId(), a.getDescricao(), a.getData(), jogoId, jogoNome);
        }).collect(Collectors.toList()));
    }
}
