package com.example.demo;

import com.example.demo.DTO.EventFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventSpecification {

    public static Specification<Event> withCriteria(EventFilterDTO criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getDate_start() != null) {
                LocalDate date = criteria.getDate_start().toLocalDate();
                LocalDateTime startOfDay = date.atStartOfDay(); //On prend de tout ceux de 00h01 le jour ou on cherche à 23h59 le jour même
                LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
                predicates.add(criteriaBuilder.between(root.get("dateBegining"), startOfDay, endOfDay));
            }

            if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), "%" + criteria.getLocation().toLowerCase() + "%"));
            }

            if (criteria.getIdCreator() != null) {
                predicates.add(criteriaBuilder.equal(root.get("idCreator").get("id"), criteria.getIdCreator()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}