package com.example.demo.Controller;


import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.Event;
import com.example.demo.Services.EventServices;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
public class EventController {

    private final EventServices event;

    @Autowired
    public EventController(EventServices event) {
        this.event = event;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return event.getAllEvents();
    }

    @PostMapping
    public Event CreateEvent(@RequestBody EventDTOCreate EventDTOCreate) {
        return event.CreateEvent(EventDTOCreate);
    }

    @PutMapping("/{id}")
    public Event UpdateEvent(@PathVariable UUID id,@RequestBody EventDTOUpdate EventDTOUpdate) {
        return event.updateEvent(EventDTOUpdate);
    }

    @DeleteMapping
    public void DeleteEvent(@PathVariable UUID id) {
        event.deleteEvent(id);
    }

    }
