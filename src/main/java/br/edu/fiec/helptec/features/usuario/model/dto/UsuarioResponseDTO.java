package br.edu.fiec.helptec.features.usuario.model.dto;

import br.edu.fiec.helptec.features.usuario.model.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private UUID idUsuario;
    private String nome;
    private String email;
    private UserRole tipoPermissao;

}
