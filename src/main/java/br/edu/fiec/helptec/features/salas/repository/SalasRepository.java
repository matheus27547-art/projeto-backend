package br.edu.fiec.helptec.features.salas.repository;

import br.edu.fiec.helptec.features.salas.Salas; // ✅ DEVE SER ESTE
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalasRepository extends JpaRepository<Salas, Long>, SalasRepositoryCustom {
}