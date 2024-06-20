package com.example.demo.exception;

public class EventCannotBeDeletedException extends RuntimeException {
    public EventCannotBeDeletedException() {
        super("Event cannot be deleted : (past)");
    }
}

