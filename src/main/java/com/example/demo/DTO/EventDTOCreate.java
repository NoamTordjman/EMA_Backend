package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventDTOCreate {
    private String title;
    private String description;
    private LocalDateTime dateBeginning;
    private String eventStatus;
    private LocalDateTime date_end;
    private String location;
    private UUID idCreator;

    public void setTitle(String title) {
        this.title = title;
    }

    public EventDTOCreate(String title, String description, LocalDateTime dateBeginning, String eventStatus, LocalDateTime date_end, String location, UUID idCreator) {
        this.title = title;
        this.description = description;
        this.dateBeginning = dateBeginning;
        this.eventStatus = "UpComing";
        this.date_end = date_end;
        this.location = location;
        this.idCreator = idCreator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateBeginning(LocalDateTime dateBeginning) {
        this.dateBeginning = dateBeginning;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setDate_end(LocalDateTime date_end) {
        this.date_end = date_end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIdCreator(UUID idCreator) {
        this.idCreator = idCreator;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateBeginning() {
        return dateBeginning;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public LocalDateTime getDate_end() {
        return date_end;
    }

    public String getLocation() {
        return location;
    }

    public UUID getIdCreator() {
        return idCreator;
    }

    public String getTitle() {
        return title;
    }

}
