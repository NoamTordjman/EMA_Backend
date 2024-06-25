package com.example.demo;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_feedback", columnDefinition = "UUID")
    private UUID id_feedback;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_registration")
    private Registration registration;

    @Column(name = "rating")
    private int rating;



    public Feedback(){}

    public Feedback(UUID id_feedback, String description, Registration registration, int rating) {
        this.id_feedback = id_feedback;
        this.description = description;
        this.registration = registration;
        this.rating = rating;
    }

    public void setId_feedback(UUID id_feedback) {
        this.id_feedback = id_feedback;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UUID getId_feedback() {
        return id_feedback;
    }

    public String getDescription() {
        return description;
    }

    public Registration getRegistration() {
        return registration;
    }

    public int getRating() {
        return rating;
    }
}
