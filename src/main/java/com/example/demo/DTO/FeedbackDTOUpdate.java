package com.example.demo.DTO;

public class FeedbackDTOUpdate {
    private int rating;
    private String comments;

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }
}
