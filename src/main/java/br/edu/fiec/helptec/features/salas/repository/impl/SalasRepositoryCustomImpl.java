package br.edu.fiec.helptec.features.salas.repository.impl;

import br.edu.fiec.helptec.features.commons.PageRequestDTO;
import br.edu.fiec.helptec.features.salas.Salas;
import br.edu.fiec.helptec.features.salas.model.dto.SalasFilterDTO;
import br.edu.fiec.helptec.features.salas.repository.SalasRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SalasRepositoryCustomImpl implements SalasRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Salas> buscarGenerica(SalasFilterDTO filtro, PageRequestDTO pageRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // 1. Query de Busca
        CriteriaQuery<Salas> query = cb.createQuery(Salas.class);
        Root<Salas> root = query.from(Salas.class);

        Predicate[] predicates = criarPredicados(cb, root, filtro);
        query.where(predicates);

        // Ordenação
        if (pageRequest.getSortBy() != null && !pageRequest.getSortBy().trim().isEmpty()) {
            Path<Object> sortPath = root.get(pageRequest.getSortBy());
            if ("desc".equalsIgnoreCase(pageRequest.getSortOrder())) {
                query.orderBy(cb.desc(sortPath));
            } else {
                query.orderBy(cb.asc(sortPath));
            }
        }

        TypedQuery<Salas> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageRequest.getPageNum() * pageRequest.getPageSize());
        typedQuery.setMaxResults(pageRequest.getPageSize());

        List<Salas> result = typedQuery.getResultList();

        // 2. Query de Total de Elementos
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Salas> countRoot = countQuery.from(Salas.class);

        Predicate[] countPredicates = criarPredicados(cb, countRoot, filtro);
        countQuery.select(cb.count(countRoot)).where(countPredicates);

        Long totalItens = entityManager.createQuery(countQuery).getSingleResult();

        // Pageable
        Sort.Direction direction = "desc".equalsIgnoreCase(pageRequest.getSortOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortBy = (pageRequest.getSortBy() != null && !pageRequest.getSortBy().trim().isEmpty()) ? pageRequest.getSortBy() : "idSala";

        Pageable pageable = PageRequest.of(pageRequest.getPageNum(), pageRequest.getPageSize(), Sort.by(direction, sortBy));

        return new PageImpl<>(result, pageable, totalItens);
    }

    private Predicate[] criarPredicados(CriteriaBuilder cb, Root<Salas> root, SalasFilterDTO filtro) {
        List<Predicate> predicates = new ArrayList<>();

        if (filtro == null) {
            return new Predicate[0];
        }

        if (filtro.getTermo() != null && !filtro.getTermo().trim().isEmpty()) {
            String pattern = "%" + filtro.getTermo().toLowerCase() + "%";
            predicates.add(cb.like(cb.lower(root.get("nome")), pattern));
        }

        if (filtro.getNome() != null && !filtro.getNome().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }
}