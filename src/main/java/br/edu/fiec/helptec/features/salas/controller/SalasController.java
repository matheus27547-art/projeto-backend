package br.edu.fiec.helptec.features.salas.controller;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.commons.PageResponseDTO;
import br.edu.fiec.helptec.features.salas.model.dto.SalasDTO;
import br.edu.fiec.helptec.features.salas.model.dto.SalasFilterDTO;
import br.edu.fiec.helptec.features.salas.service.SalasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/salas")
public class SalasController {

    @Autowired
    private SalasService salasService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<SalasDTO>> buscar(
            SalasFilterDTO filtro,
            PageRequestDTO pageRequest
    ) {
        PageResponseDTO<SalasDTO> response = salasService.buscarComFiltro(filtro, pageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalasDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salasService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SalasDTO> criar(@RequestBody SalasDTO dto) {
        SalasDTO criada = salasService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalasDTO> atualizar(@PathVariable Long id, @RequestBody SalasDTO dto) {
        SalasDTO atualizada = salasService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        salasService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}