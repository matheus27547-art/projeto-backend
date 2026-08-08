package br.edu.fiec.helptec.features.salas.repository;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.salas.Salas;
import br.edu.fiec.helptec.features.salas.model.dto.SalasFilterDTO;
import org.springframework.data.domain.Page;

public interface SalasRepositoryCustom {
    Page<Salas> buscarGenerica(SalasFilterDTO filtro, PageRequestDTO pageRequest);
}
