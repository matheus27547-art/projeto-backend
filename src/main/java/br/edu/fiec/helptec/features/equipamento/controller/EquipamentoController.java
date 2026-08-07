package br.edu.fiec.helptec.features.equipamento.controller;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.equipamento.model.dto.EquipamentoDTO;
import br.edu.fiec.helptec.features.equipamento.model.dto.EquipamentoFilterDTO;
import br.edu.fiec.helptec.features.commons.PageResponseDTO;
import br.edu.fiec.helptec.features.equipamento.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<PageResponseDTO<EquipamentoDTO>> buscar(
            EquipamentoFilterDTO filtro,
            PageRequestDTO pageRequest
    ) {
        PageResponseDTO<EquipamentoDTO> response = equipamentoService.buscarComFiltro(filtro, pageRequest);
        return ResponseEntity.ok(response);
    }


    // GET (Buscar único por ID)
    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipamentoService.buscarPorId(id));
    }

    // POST (Criar)
    @PostMapping
    public ResponseEntity<EquipamentoDTO> criar(@RequestBody EquipamentoDTO dto) {
        EquipamentoDTO criado = equipamentoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    // PUT (Atualizar)
    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> atualizar(@PathVariable Long id, @RequestBody EquipamentoDTO dto) {
        EquipamentoDTO atualizado = equipamentoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE (Remover)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        equipamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}