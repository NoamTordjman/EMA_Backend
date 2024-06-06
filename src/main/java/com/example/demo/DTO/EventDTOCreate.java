package com.example.demo.DTO;

import com.example.demo.User;

import java.time.LocalDateTime;

public class EventDTOCreate {
    private String title;
    private String description;
    private LocalDateTime dateBegining;
    private String eventStatus;
    private LocalDateTime date_end;
    private String location;
    private User idCreator;

    public void setTitle(String title) {
        this.title = title;
    }

    public EventDTOCreate(String title, String description, LocalDateTime dateBegining, String eventStatus, LocalDateTime date_end, String location, User idCreator) {
        this.title = title;
        this.description = description;
        this.dateBegining = dateBegining;
        this.eventStatus = eventStatus;
        this.date_end = date_end;
        this.location = location;
        this.idCreator = idCreator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateBegining(LocalDateTime dateBegining) {
        this.dateBegining = dateBegining;
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

    public void setIdCreator(User idCreator) {
        this.idCreator = idCreator;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateBegining() {
        return dateBegining;
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

    public User getIdCreator() {
        return idCreator;
    }

    public String getTitle() {
        return title;
    }

}
