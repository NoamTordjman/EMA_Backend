package com.example.demo.DTO;

import java.time.LocalDateTime;


public class EventDTOUpdate {
    private String title;
    private String description;
    private LocalDateTime dateBegining;
    private String eventStatus;
    private LocalDateTime date_end;
    private String location;

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getDate_end() {
        return date_end;
    }

    public LocalDateTime getDateBegining() {
        return dateBegining;
    }

    public String getEventStatus() {
        return eventStatus;
    }
}
