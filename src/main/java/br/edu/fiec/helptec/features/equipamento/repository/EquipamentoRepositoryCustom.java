package br.edu.fiec.helptec.features.equipamento.repository;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.equipamento.model.dto.EquipamentoFilterDTO;
import org.springframework.data.domain.Page;
import br.edu.fiec.helptec.features.equipamento.Equipamento;

public interface EquipamentoRepositoryCustom {
    Page<Equipamento> buscarGenerica(EquipamentoFilterDTO filtro, PageRequestDTO pageRequest);
}