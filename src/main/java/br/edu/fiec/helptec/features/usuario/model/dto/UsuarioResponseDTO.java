package br.edu.fiec.helptec.features.usuario.model.dto;

public class UsuarioResponseDTO {

    private Integer idUsuario;
    private String uuidUsuario;
    private String nome;
    private String email;
    private Integer tipoPermissao;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Integer idUsuario, String uuidUsuario, String nome, String email, Integer tipoPermissao) {
        this.idUsuario = idUsuario;
        this.uuidUsuario = uuidUsuario;
        this.nome = nome;
        this.email = email;
        this.tipoPermissao = tipoPermissao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUuidUsuario() {
        return uuidUsuario;
    }

    public void setUuidUsuario(String uuidUsuario) {
        this.uuidUsuario = uuidUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipoPermissao() {
        return tipoPermissao;
    }

    public void setTipoPermissao(Integer tipoPermissao) {
        this.tipoPermissao = tipoPermissao;
    }
}
