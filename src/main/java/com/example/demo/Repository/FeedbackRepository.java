package com.example.demo.Repository;

import com.example.demo.Event;
import com.example.demo.Feedback;
import com.example.demo.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    List<Feedback> findByRegistrationIn(List<Registration> registrations);

    @Query("SELECT f FROM Feedback f WHERE f.registration IN (SELECT r FROM Registration r WHERE r.event = :event)")
    List<Feedback> findByEvent(@Param("event") Event event);
}
