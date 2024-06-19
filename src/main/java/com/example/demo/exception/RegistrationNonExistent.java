package com.example.demo.exception;

import java.util.UUID;

public class RegistrationNonExistent extends RuntimeException {
    public RegistrationNonExistent(UUID id) {
        super("Registration non-existent for user: " + id);
    }
}
