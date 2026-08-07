package br.edu.fiec.helptec.features.usuario.service.impl;

import br.edu.fiec.helptec.features.usuario.model.dto.CreateUsuarioRequestDTO;
import br.edu.fiec.helptec.features.usuario.model.dto.TokenRequestDTO;
import br.edu.fiec.helptec.features.usuario.model.entity.UsuarioEntity;
import br.edu.fiec.helptec.features.usuario.repository.UsuarioRepository;
import br.edu.fiec.helptec.features.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;



    @Override
    public void createUsuario(CreateUsuarioRequestDTO createUsuarioRequestDTO) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setEmail(createUsuarioRequestDTO.email());
        usuarioEntity.setName(createUsuarioRequestDTO.nome());;
        usuarioEntity.setPassword(createUsuarioRequestDTO.passowrd());
        usuarioRepository.save(usuarioEntity);

    }

    @Override
    public void setToken(TokenRequestDTO tokenRequestDTO) {
        List<UsuarioEntity> usuarioEntityList = usuarioRepository.findAll();
        UsuarioEntity usuarioEntity = usuarioEntityList.stream().findFirst().orElseThrow();
        usuarioEntity.setFcmToken(tokenRequestDTO.token());
        usuarioRepository.save(usuarioEntity);

    }
}
