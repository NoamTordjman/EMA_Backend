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
import com.example.demo.exception.EventCannotBeDeletedException;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventServices {

    private final EventRepository repository;
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Autowired
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
        User creator = userRepository.findById(EventDTO.getIdCreator()).orElseThrow(()-> new UserNonExistent(EventDTO.getIdCreator()));
        event.setCreator(creator);
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }


    @Override
    public Event updateEvent(UUID EventID, EventDTOUpdate EventDTO){
        Event event = repository.findById(EventID).orElseThrow(()-> new EventNonExistant(EventID));
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
        Event event = repository.findById(idEvent).orElseThrow(()-> new EventNonExistant(idEvent));
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime date_end = event.getDate_end();

        if(date_end.isBefore(now)){
            throw new EventCannotBeDeletedException();
        }

        repository.deleteById(idEvent);
    }

    @Override
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public Event getEventById(UUID idEvent) {
        Event event = repository.findById(idEvent).orElseThrow(()-> new EventNonExistant(idEvent));
        return repository.findById(idEvent).orElse(null);
    }

    @Override
    public List<Event> searchEvents(EventFilterDTO eventFilterDTO) {
        Specification<Event> specification = EventSpecification.withCriteria(eventFilterDTO);
        return eventRepository.findAll(specification);
        }


    @Override
    public List<Event> getEventByMember(UUID memberId) {
        User user = userRepository.findById(memberId).orElseThrow(()-> new UserNonExistent(memberId));
        List<Registration> registrations = registrationRepository.findByUser(user);
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(memberId);
        }
        List<Event> events = new ArrayList<>();
        for (Registration registration : registrations) {
            events.add(registration.getEvent());
        }
        events.sort(Comparator.comparing(Event::getDateBegining).reversed());

        Set<Event> uniqueEvents = new LinkedHashSet<>(events);
        List<Event> uniqueEventsList = new ArrayList<>(uniqueEvents);

        return uniqueEventsList;
    }

    @Override
    public List<Event> getEventByNonRegisteredMember(UUID memberId) {
        User user = userRepository.findById(memberId).orElseThrow(()-> new UserNonExistent(memberId));;
        List<Registration> registrations = registrationRepository.findByUser(user);
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(memberId);
        }
        List<Event> allEvents = eventRepository.findAll();
        List<Event> nonRegisteredEvents = new ArrayList<>();

        for (Event event : allEvents) {
            boolean isRegistered = false;
            for (Registration registration : registrations) {
                if (registration.getEvent().equals(event)) {
                    isRegistered = true;
                    break;
                }
            }
            if (!isRegistered) {
                nonRegisteredEvents.add(event);
            }
        }

        nonRegisteredEvents.sort(Comparator.comparing(Event::getDateBegining).reversed());

        Set<Event> uniqueEvents = new LinkedHashSet<>(nonRegisteredEvents);
        List<Event> uniqueEventsList = new ArrayList<>(uniqueEvents);

        if(uniqueEventsList.isEmpty()){
            throw new EventNonExistant(memberId);
        }

        return uniqueEventsList;
    }



}
