package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_event", columnDefinition = "UUID")
    private UUID idEvent;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;


    @Column(name="date_Beginning")
    @JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateBegining;

    @Column(name="statut")
    private String eventStatus;

    @Column(name="date_end")
    @JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date_end;

    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(name="id_creator")
    private User idCreator;

    public Event() {
    }

    public Event(String title, String description, LocalDateTime dateBegining, String eventStatus, LocalDateTime date_end, String location, User idCreator) {
        this.title = title;
        this.description = description;
        this.dateBegining = dateBegining;
        this.eventStatus = eventStatus;
        this.date_end = date_end;
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

    public void setDateBegining(LocalDateTime dateBegining) {
        this.dateBegining = dateBegining;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate_end(LocalDateTime duration) {
        this.date_end = duration;
    }

    public void setCreator(User idCreator) {
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
}
