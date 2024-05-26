package com.example.demo.DTO;
import com.example.demo.Enum.EventStatus;
import java.time.LocalDate;
import java.time.LocalTime;


public class EventDTOUpdate {
    private String title;
    private String description;
    private LocalDate dateBegining;
    private EventStatus eventStatus;
    private LocalTime duration;
    private String location;

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public LocalDate getDateBegining() {
        return dateBegining;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }
}
