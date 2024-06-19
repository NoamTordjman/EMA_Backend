package com.example.demo.exception;

public class EventNonExistant extends RuntimeException {
    public EventNonExistant(String message) {
        super(message);
    }
}
