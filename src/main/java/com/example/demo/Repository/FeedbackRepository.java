package com.example.demo.Repository;

import com.example.demo.Feedback;
import com.example.demo.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    List<Feedback> findByRegistrationIn(List<Registration> registrations);

}
