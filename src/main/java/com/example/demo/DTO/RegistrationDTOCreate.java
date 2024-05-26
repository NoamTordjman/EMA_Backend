package com.example.demo.DTO;

import java.util.UUID;

public class RegistrationDTOCreate {
    private UUID userId;

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getEventId() {
        return eventId;
    }

    private UUID eventId;
}
