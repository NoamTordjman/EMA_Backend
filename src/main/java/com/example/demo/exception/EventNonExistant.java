package com.example.demo.exception;

import java.util.UUID;

public class EventNonExistant extends RuntimeException {
    public EventNonExistant(UUID id) {
        super("Event " + id + " does not exist");
    }
}
