package com.example.demo.exception;

import java.util.UUID;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(UUID id) {
        super("User: " + id + " already registered");
    }
}
