package com.example.demo.exception;

public class EventCannotBeDeletedException extends RuntimeException {
    public EventCannotBeDeletedException(String message) {
        super(message);
    }
}

