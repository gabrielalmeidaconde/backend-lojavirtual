package com.steem.lojavirtual.genero;

import com.steem.lojavirtual.genero.dto.CreateGeneroDTO;
import com.steem.lojavirtual.genero.dto.EditGeneroDTO;
import com.steem.lojavirtual.genero.dto.ResponseGeneroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroServices service;

    public GeneroController(GeneroServices service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseGeneroDTO> create(@RequestBody CreateGeneroDTO dto) {
        Genero g = service.create(dto.nome());
        return new ResponseEntity<>(new ResponseGeneroDTO(g.getId(), g.getNome()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseGeneroDTO> edit(@RequestBody EditGeneroDTO dto) {
        Genero g = service.edit(dto.id(), dto.nome());
        return ResponseEntity.ok(new ResponseGeneroDTO(g.getId(), g.getNome()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGeneroDTO> getById(@PathVariable Long id) {
        Genero g = service.findById(id);
        return ResponseEntity.ok(new ResponseGeneroDTO(g.getId(), g.getNome()));
    }

    @GetMapping
    public ResponseEntity<List<ResponseGeneroDTO>> listAll() {
        return ResponseEntity.ok(service.listAll().stream().map(g -> new ResponseGeneroDTO(g.getId(), g.getNome())).collect(Collectors.toList()));
    }
}
