package br.edu.fiec.helptec.features.usuario.service.impl;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.commons.PageResponseDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.CreateUsuarioRequestDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.UsuarioResponseDTO;
import br.edu.fiec.helptec.features.usuario.model.entity.UsuarioEntity;
import br.edu.fiec.helptec.features.usuario.repository.UsuarioRepository;
import br.edu.fiec.helptec.features.usuario.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDTO criar(CreateUsuarioRequestDTO request) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setSenha(request.getSenha());
        entity.setTipoPermissao(request.getTipoPermissao());

        UsuarioEntity salvo = usuarioRepository.save(entity);
        return toDTO(salvo);
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponseDTO<UsuarioResponseDTO> listarPaginado(PageRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UsuarioEntity> page = usuarioRepository.findAll(pageable);

        List<UsuarioResponseDTO> conteudo = page.getContent().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new PageResponseDTO<>(
                conteudo,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Integer id) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return toDTO(entity);
    }

    @Override
    public UsuarioResponseDTO atualizar(Integer id, CreateUsuarioRequestDTO request) {
        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        if (request.getSenha() != null && !request.getSenha().isBlank()) {
            entity.setSenha(request.getSenha());
        }
        entity.setTipoPermissao(request.getTipoPermissao());

        UsuarioEntity atualizado = usuarioRepository.save(entity);
        return toDTO(atualizado);
    }

    @Override
    public void deletar(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toDTO(UsuarioEntity entity) {
        return new UsuarioResponseDTO(
                entity.getIdUsuario(),
                entity.getUuidUsuario(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTipoPermissao()
        );
    }
}