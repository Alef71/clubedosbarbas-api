package br.com.clubedosbarbas.api.domain.Agendamento.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.clubedosbarbas.api.domain.Agendamento.Agendamento;
import jakarta.persistence.criteria.Predicate;

public class AgendamentoSpecifications {

    @SuppressWarnings("CollectionsToArray")
    public static Specification<Agendamento> comFiltros(Long barbeiroId, Long clienteId, LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (barbeiroId != null) {
                predicates.add(criteriaBuilder.equal(root.get("barbeiro").get("id"), barbeiroId));
            }
            if (clienteId != null) {
                predicates.add(criteriaBuilder.equal(root.get("cliente").get("id"), clienteId));
            }
            if (dataInicio != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataHora"), dataInicio.atStartOfDay()));
            }
            if (dataFim != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataHora"), dataFim.atTime(23, 59, 59)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
