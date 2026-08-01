package br.edu.fiec.helptec.features.usuario.controller;

import br.edu.fiec.helptec.features.usuario.model.dto.CreateUsuarioRequestDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.TokenRequestDTO;
import br.edu.fiec.helptec.features.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private UsuarioService usuarioService;

    @PostMapping
    ResponseEntity<Void> createUsuario (@RequestBody CreateUsuarioRequestDTO createUsuarioRequestDTO){
        usuarioService.createUsuario(createUsuarioRequestDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    ResponseEntity<Void> setToken (@RequestBody TokenRequestDTO tokenRequestDTO){
        usuarioService.setToken(tokenRequestDTO);
        return ResponseEntity.status(200).build();
    }

}
