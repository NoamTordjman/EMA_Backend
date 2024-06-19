package com.example.demo.Services;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.EventFilterDTO;
import com.example.demo.Event;

import java.util.List;
import java.util.UUID;

public interface EventServices {

    Event CreateEvent(EventDTOCreate Event);
    void deleteEvent(UUID idEvent);
    Event updateEvent(UUID EventID, EventDTOUpdate Event);
    List<Event> getAllEvents();
    Event getEventById(UUID idEvent);
    List<Event> searchEvents(EventFilterDTO eventFilterDTO);
    List<Event> getEventByMember(UUID memberId);
    List<Event> getEventByNonRegisteredMember(UUID memberId);
}
