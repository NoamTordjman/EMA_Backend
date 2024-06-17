package com.example.demo.DTO;

import com.example.demo.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventFilterDTO {

    private LocalDateTime date;
    private String location;
    private UUID idCreator;

    public EventFilterDTO() {
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setIdCreator(UUID idCreator) {
        this.idCreator = idCreator;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public UUID getIdCreator() {
        return idCreator;
    }
}
