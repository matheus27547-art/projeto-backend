package br.edu.fiec.helptec.features.usuario.model.dto;

public record CreateUsuarioRequestDTO (
        String email,
        String passowrd,
        String nome
){
}
