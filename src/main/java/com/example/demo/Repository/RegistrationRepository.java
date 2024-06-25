package com.example.demo.Repository;

import com.example.demo.Event;
import com.example.demo.Registration;
import com.example.demo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    List<Registration> findByUser(User user);
    List<Registration> findByEventIn(List<Event> events);

    Boolean existsByUserAndEvent(User user, Event event);

}
