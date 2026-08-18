package br.edu.fiec.helptec.features.usuario.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUsuarioRequestDTO {

    // Getters e Setters
    private String nome;
    private String email;
    private String senha;
    private Integer tipoPermissao;

}