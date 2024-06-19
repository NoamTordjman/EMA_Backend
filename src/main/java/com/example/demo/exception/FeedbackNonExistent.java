package com.example.demo.exception;

import java.util.UUID;

public class FeedbackNonExistent extends RuntimeException {
    public FeedbackNonExistent(UUID id) {
        super("Feedback Not Found on Id: " + id);
    }
}
