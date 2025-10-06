package br.com.clubedosbarbas.api.domain.Chat.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.clubedosbarbas.api.domain.Chat.Mensagem;
import jakarta.persistence.criteria.Predicate;

public class MensagemSpecifications {

    public static Specification<Mensagem> comFiltros(Long usuario1Id, Long usuario2Id, LocalDate dataInicio, LocalDate dataFim) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            Predicate conversaEntreUsuarios = criteriaBuilder.or(
                criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("remetenteId"), usuario1Id),
                    criteriaBuilder.equal(root.get("destinatarioId"), usuario2Id)
                ),
                criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("remetenteId"), usuario2Id),
                    criteriaBuilder.equal(root.get("destinatarioId"), usuario1Id)
                )
            );
            predicates.add(conversaEntreUsuarios);

            if (dataInicio != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataEnvio"), dataInicio.atStartOfDay()));
            }
            if (dataFim != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataEnvio"), dataFim.atTime(23, 59, 59)));
            }

            
            if (query != null) {
                query.orderBy(criteriaBuilder.asc(root.get("dataEnvio")));
            }

            
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
