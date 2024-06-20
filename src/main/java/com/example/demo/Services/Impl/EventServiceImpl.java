package com.example.demo.Services.Impl;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.EventFilterDTO;
import com.example.demo.Event;
import com.example.demo.EventSpecification;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Services.EventServices;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.EventCannotBeDeletedException;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;


import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventServices {

    @Autowired
    private final EventRepository repository;
    private final RegistrationServices registrationService;
    private final UserServices userService;

    @Autowired
    public EventServiceImpl(EventRepository repository, RegistrationServices registrationService, UserServices userService) {
        this.repository = repository;
        this.registrationService = registrationService;
        this.userService = userService;
    }



    @Override
    public Event CreateEvent( EventDTOCreate EventDTO) throws UserNonExistent{
        Event event = new Event();
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBeginning());
        event.setDate_end(EventDTO.getDate_end());
        User creator = userService.getUserById(EventDTO.getIdCreator());
        event.setCreator(creator);
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }


    @Override
    public Event updateEvent(UUID EventID, EventDTOUpdate EventDTO) throws EventNonExistant {
        Event event = getEventById(EventID);
        event.setTitle(EventDTO.getTitle());
        event.setDescription(EventDTO.getDescription());
        event.setEventStatus(EventDTO.getEventStatus());
        event.setDateBegining(EventDTO.getDateBegining());
        event.setDate_end(EventDTO.getDate_end());
        event.setLocation(EventDTO.getLocation());
        return repository.save(event);
    }

    @Override
    public void deleteEvent(UUID idEvent) throws EventNonExistant {
        Event event = getEventById(idEvent);
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
    public List<Event> getEventByIdCreator(UUID Id_Creator) throws UserNonExistent{
        User user = userService.getUserById(Id_Creator);
        return repository.findByIdCreator(user);
    }


    @Override
    public Event getEventById(UUID idEvent) throws EventNonExistant {
        Event event = repository.findById(idEvent).orElseThrow(()-> new EventNonExistant(idEvent));
        return repository.findById(idEvent).orElse(null);
    }

    @Override
    public List<Event> searchEvents(EventFilterDTO eventFilterDTO) {
        Specification<Event> specification = EventSpecification.withCriteria(eventFilterDTO);
        return repository.findAll(specification);
        }


    @Override
    public Set<Event> getEventByMember(UUID memberId) throws UserNonExistent,RegistrationNonExistent{
        User user = userService.getUserById(memberId);
        List<Registration> registrations = registrationService.getRegistrationByUserId(user.getId());
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(memberId);
        }
        List<Event> events = new ArrayList<>();

        for (Registration registration : registrations) {
            events.add(registration.getEvent());
        }
        events.sort(Comparator.comparing(Event::getDateBegining).reversed());

        Set<Event> uniqueEvents = new LinkedHashSet<>(events);


        return uniqueEvents;
    }

    @Override
    public List<Event> getEventByNonRegisteredMember(UUID memberId) throws UserNonExistent,RegistrationNonExistent,EventNonExistant {
        User user = userService.getUserById(memberId);
        List<Registration> registrations = registrationService.getRegistrationByUserId(user.getId());
        if (registrations.isEmpty()) {
            throw new RegistrationNonExistent(memberId);
        }
        List<Event> allEvents = repository.findAll();
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
