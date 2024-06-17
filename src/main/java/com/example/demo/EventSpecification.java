package com.example.demo;

import com.example.demo.DTO.EventFilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventSpecification {

    public static Specification<Event> withCriteria(EventFilterDTO criteria){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(criteria.getDate() != null){
                LocalDateTime date = criteria.getDate();
                predicates.add(criteriaBuilder.equal(root.get("dateBegining"), date));
            }

            if(criteria.getLocation() != null && !criteria.getLocation().isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), "%" + criteria.getLocation().toLowerCase() + "%"));
            }

            if(criteria.getIdCreator() != null){
                predicates.add(criteriaBuilder.equal(root.get("idCreator").get("id"), criteria.getIdCreator()));
            }

            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));

        };
    }
}
