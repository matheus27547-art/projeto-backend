package br.edu.fiec.helptec.features.usuario.repository;

import br.edu.fiec.helptec.features.usuario.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
