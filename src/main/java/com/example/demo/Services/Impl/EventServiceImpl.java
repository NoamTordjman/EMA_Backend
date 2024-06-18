package com.example.demo.Services.Impl;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.EventFilterDTO;
import com.example.demo.Event;
import com.example.demo.EventSpecification;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.RegistrationRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.EventServices;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventServices {

    private final EventRepository repository;
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Autowired // Ajoutez cette annotation pour l'injection de dépendances
    public EventServiceImpl(EventRepository repository, EventRepository eventRepository, RegistrationRepository registrationRepository, UserRepository userRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
    }



    @Override
    public Event CreateEvent(@RequestBody EventDTOCreate EventDTO){
        Event event = new Event();
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBegining());
        event.setDate_end(EventDTO.getDate_end());
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
        event.setDate_end(EventDTO.getDate_end());
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

    @Override
    public List<Event> searchEvents(EventFilterDTO eventFilterDTO) {
        Specification<Event> specification = EventSpecification.withCriteria(eventFilterDTO);
        return eventRepository.findAll(specification);
        }

    @Override
    public List<Event> getEventByMember(UUID memberId){
        User user = userRepository.findById(memberId).orElse(null);
        List<Registration> registrations = registrationRepository.findByUser(user);
        return eventRepository.findByRegistrationIn(registrations);
    }


}
