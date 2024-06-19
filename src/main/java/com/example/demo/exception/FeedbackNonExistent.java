package com.example.demo.exception;

public class FeedbackNonExistent extends RuntimeException {
    public FeedbackNonExistent(String message) {
        super(message);
    }
}
