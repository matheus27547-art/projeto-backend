package br.edu.fiec.helptec.features.equipamento.repository;

import br.edu.fiec.helptec.features.equipamento.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>, EquipamentoRepositoryCustom {
}
