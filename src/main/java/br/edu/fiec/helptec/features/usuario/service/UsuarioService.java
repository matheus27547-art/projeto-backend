package br.edu.fiec.helptec.features.usuario.service;

import br.edu.fiec.helptec.features.usuario.model.dto.CreateUsuarioRequestDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.TokenRequestDTO;

public interface UsuarioService {
    void createUsuario(CreateUsuarioRequestDTO createUsuarioRequestDTO);

    void setToken(TokenRequestDTO tokenRequestDTO);
}
