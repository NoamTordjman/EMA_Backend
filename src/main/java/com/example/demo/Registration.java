package com.example.demo;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_registration", columnDefinition = "UUID")
    private UUID registrationID;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_event",nullable = false)
    private Event event;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.REMOVE)
    private Set<Feedback> registrationSet;

    public Registration() {}

    public Registration(UUID registration, User user, Event event) {
        this.registrationID = registration;
        this.user = user;
        this.event = event;
    }

    public void setRegistrationID(UUID registrationID) {
        this.registrationID = registrationID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public UUID getRegistrationID() {
        return registrationID;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

}
