package com.example.demo.DTO;

import java.util.UUID;

public class RegistrationDTOCreate {
    private UUID userId;
    private UUID eventId;

    public RegistrationDTOCreate(UUID userId, UUID eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }

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

}
