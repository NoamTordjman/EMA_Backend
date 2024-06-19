package com.example.demo.exception;

public class FeedbackOnNonExistentRegistrationException extends RuntimeException{
    public FeedbackOnNonExistentRegistrationException(String message) {
        super(message);
    }
}
