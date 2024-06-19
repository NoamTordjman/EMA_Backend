package com.example.demo.exception;

public class RegistrationNonExistent extends RuntimeException {
    public RegistrationNonExistent(String message) {
        super(message);
    }
}
