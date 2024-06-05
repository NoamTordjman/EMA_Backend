package com.example.demo.Services.Impl;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.Event;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventServices {

    private final EventRepository repository;

    @Autowired // Ajoutez cette annotation pour l'injection de dépendances
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }



    @Override
    public Event CreateEvent(@RequestBody EventDTOCreate EventDTO){
        Event event = new Event();
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBegining());
        event.setDuration(EventDTO.getDuration());
        event.setIdCreator(EventDTO.getIdCreator());
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }

    @Override
    public Event updateEvent(UUID EventID, EventDTOUpdate EventDTO){
        Event event = repository.findById(EventID).orElse(null);
        assert event != null;
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBegining());
        event.setDuration(EventDTO.getDuration());
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }

    @Override
    public void deleteEvent(UUID idEvent) {
        repository.deleteById(idEvent);
    }

    @Override
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public Event getEventById(UUID idEvent) {
        return repository.findById(idEvent).orElse(null);
    }


}
