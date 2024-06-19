package com.example.demo.exception;

public class UserNonExistent extends RuntimeException {
    public UserNonExistent(String message) {
        super(message);
    }
}
