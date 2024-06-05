package com.example.demo;

import com.example.demo.Enum.RegistrationStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @Column(name = "id_registration")
    private UUID registrationID;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @Column(name = "status")
    private RegistrationStatus status;

    public Registration() {}

    public Registration(UUID registration, User user, Event event, RegistrationStatus status) {
        this.registrationID = registration;
        this.user = user;
        this.event = event;
        this.status = status;
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

    public void setStatus(RegistrationStatus status) {
        this.status = status;
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

    public RegistrationStatus getStatus() {
        return status;
    }

}
