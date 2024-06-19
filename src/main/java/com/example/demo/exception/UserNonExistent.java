package com.example.demo.exception;

import java.util.UUID;


public class UserNonExistent extends RuntimeException {
    public UserNonExistent(UUID id) {
        super("User non-existent: " + id);
    }
}
