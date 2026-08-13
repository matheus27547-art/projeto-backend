package br.edu.fiec.helptec.features.usuario.service;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.commons.PageResponseDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.CreateUsuarioRequestDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO criar(CreateUsuarioRequestDTO request);
    List<UsuarioResponseDTO> listarTodos();
    PageResponseDTO<UsuarioResponseDTO> listarPaginado(PageRequestDTO pageRequest);
    UsuarioResponseDTO buscarPorId(Integer id);
    UsuarioResponseDTO atualizar(Integer id, CreateUsuarioRequestDTO request);
    void deletar(Integer id);
}