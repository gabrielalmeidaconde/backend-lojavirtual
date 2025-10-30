package com.steem.lojavirtual.empresa;

import com.steem.lojavirtual.empresa.dto.CreateEmpresaDTO;
import com.steem.lojavirtual.empresa.dto.EditEmpresaDTO;
import com.steem.lojavirtual.empresa.dto.ResponseEmpresaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseEmpresaDTO> create(@RequestBody CreateEmpresaDTO dto) {
        Empresa e = service.create(dto.nome());
        return new ResponseEntity<>(new ResponseEmpresaDTO(e.getId(), e.getNome()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseEmpresaDTO> edit(@RequestBody EditEmpresaDTO dto) {
        Empresa e = service.edit(dto.id(), dto.nome());
        return ResponseEntity.ok(new ResponseEmpresaDTO(e.getId(), e.getNome()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEmpresaDTO> getById(@PathVariable Long id) {
        Empresa e = service.findById(id);
        return ResponseEntity.ok(new ResponseEmpresaDTO(e.getId(), e.getNome()));
    }

    @GetMapping
    public ResponseEntity<List<ResponseEmpresaDTO>> listAll() {
        return ResponseEntity.ok(service.listAll().stream().map(e -> new ResponseEmpresaDTO(e.getId(), e.getNome())).collect(Collectors.toList()));
    }
}
