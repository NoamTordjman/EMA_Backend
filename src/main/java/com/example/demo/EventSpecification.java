package com.example.demo;

import com.example.demo.DTO.EventFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de spécification pour la construction de critères de recherche pour les événements.
 * Utilise les critères fournis pour générer des prédicats utilisables dans les requêtes de base de données.
 */
public class EventSpecification {

    /**
     * Construit une spécification pour filtrer les événements basée sur les critères fournis.
     *
     * @param criteria DTO contenant les critères de filtrage des événements.
     * @return Une spécification construite selon les critères donnés.
     */
    public static Specification<Event> withCriteria(EventFilterDTO criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtrage par date de début
            if (criteria.getDate_start() != null) {
                LocalDate date = criteria.getDate_start().toLocalDate();
                LocalDateTime startOfDay = date.atStartOfDay(); // Début de la journée
                LocalDateTime endOfDay = date.plusDays(1).atStartOfDay(); // Fin de la journée
                predicates.add(criteriaBuilder.between(root.get("dateBegining"), startOfDay, endOfDay));
            }

            // Filtrage par emplacement
            if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), "%" + criteria.getLocation().toLowerCase() + "%"));
            }

            // Filtrage par identifiant du créateur
            if (criteria.getIdCreator() != null) {
                predicates.add(criteriaBuilder.equal(root.get("idCreator").get("id"), criteria.getIdCreator()));
            }

            // Combine tous les prédicats avec un ET logique pour former la spécification finale.
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
