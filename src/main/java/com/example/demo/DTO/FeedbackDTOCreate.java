package com.example.demo.DTO;

import java.util.UUID;

public class FeedbackDTOCreate {
    private UUID RegistrationId;
    private int rating;
    private String description;

    public FeedbackDTOCreate(UUID registrationId, int rating, String description) {
        RegistrationId = registrationId;
        this.rating = rating;
        this.description = description;
    }

    public void setRegistrationId(UUID registrationId) {
        RegistrationId = registrationId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getRegistrationId() {
        return RegistrationId;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }
}
