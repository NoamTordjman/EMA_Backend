package com.example.demo;

import com.example.demo.Enum.EventStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @Column(name="id_event")
    private UUID idEvent;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="date_Beginning")
    private LocalDate dateBegining;

    @Column(name="statut")
    private EventStatus eventStatus;

    @Column(name="duration")
    private LocalTime duration;

    @Column(name="location")
    private String location;

    @Column(name="id_creator")
    private UUID idCreator;

    public Event() {
    }

    public Event(String title, String description, LocalDate dateBegining, EventStatus eventStatus, LocalTime duration, String location, UUID idCreator) {
        this.title = title;
        this.description = description;
        this.dateBegining = dateBegining;
        this.eventStatus = eventStatus;
        this.duration = duration;
        this.location = location;
        this.idCreator = idCreator;

        //Id event !!!!!
    }

    public void setIdEvent(UUID idEvent) {
        this.idEvent = idEvent;
    }

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

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setIdCreator(UUID idCreator) {
        this.idCreator = idCreator;
    }

    public String getTitle() {
        return title;
    }

    public UUID getIdEvent() {
        return idEvent;
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
}
