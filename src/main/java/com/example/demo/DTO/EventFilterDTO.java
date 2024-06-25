package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventFilterDTO {

    private LocalDateTime date_start;
    private String location;
    private UUID idCreator;

    public EventFilterDTO() {
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate_start(LocalDateTime date_start) {
        this.date_start = date_start;
    }

    public void setIdCreator(UUID idCreator) {
        this.idCreator = idCreator;
    }

    public LocalDateTime getDate_start() {
        return date_start;
    }

    public String getLocation() {
        return location;
    }

    public UUID getIdCreator() {
        return idCreator;
    }
}
