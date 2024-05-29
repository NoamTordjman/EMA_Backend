package com.example.demo.DTO;

import com.example.demo.Enum.EventStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class EventDTOCreate {
    private String title;
    private String description;
    private LocalDate dateBegining;
    private EventStatus eventStatus;
    private LocalTime duration;
    private String location;
    private UUID idCreator;

    public void setTitle(String title) {
        this.title = title;
    }

    public EventDTOCreate(String title, String description, LocalDate dateBegining, EventStatus eventStatus, LocalTime duration, String location, UUID idCreator) {
        this.title = title;
        this.description = description;
        this.dateBegining = dateBegining;
        this.eventStatus = eventStatus;
        this.duration = duration;
        this.location = location;
        this.idCreator = idCreator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateBegining(LocalDate dateBegining) {
        this.dateBegining = dateBegining;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
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

    public LocalDate getDateBegining() {
        return dateBegining;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public LocalTime getDuration() {
        return duration;
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
