package com.example.demo.DTO;

import java.util.UUID;

public class FeedbackDTOCreate {
    private UUID RegistrationId;
    private int rating;
    private String comments;

    public void setRegistrationId(UUID registrationId) {
        RegistrationId = registrationId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public UUID getRegistrationId() {
        return RegistrationId;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }
}
