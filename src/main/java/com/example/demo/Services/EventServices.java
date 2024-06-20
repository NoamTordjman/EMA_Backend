package com.example.demo.Services;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.EventFilterDTO;
import com.example.demo.Event;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface EventServices {

    Event CreateEvent(EventDTOCreate Event) throws UserNonExistent;
    void deleteEvent(UUID idEvent) throws EventNonExistant;
    Event updateEvent(UUID EventID, EventDTOUpdate Event)throws EventNonExistant;
    List<Event> getAllEvents();

    List<Event> getEventByIdCreator(UUID Id_Creator) throws UserNonExistent;

    Event getEventById(UUID idEvent) throws EventNonExistant;
    List<Event> searchEvents(EventFilterDTO eventFilterDTO);
    Set<Event> getEventByMember(UUID memberId)throws UserNonExistent, RegistrationNonExistent;
    List<Event> getEventByNonRegisteredMember(UUID memberId) throws UserNonExistent, RegistrationNonExistent,EventNonExistant;
}
