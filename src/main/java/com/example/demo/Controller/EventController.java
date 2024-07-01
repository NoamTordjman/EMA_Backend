package com.example.demo.Controller;


import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.EventFilterDTO;
import com.example.demo.Event;
import com.example.demo.Services.EventServices;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserNonExistent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@Tag(
        name="Event Controller API",
        description = "Permit to control the Event"
)
@RequestMapping("/v1/Event")
public class  EventController {

    private final EventServices event;

    @Autowired
    public EventController(EventServices event) {
        this.event = event;
    }

    @GetMapping("/getall")
    public List<Event> getAllEvents() {
        return event.getAllEvents();
    }

    @GetMapping("/getbyidcreator/{id}")
    public List<Event> getAllEvents(@PathVariable UUID id) {
        return event.getEventByIdCreator(id);
    }

    @PostMapping("/create")
    public Event CreateEvent(@RequestBody EventDTOCreate EventDTOCreate) {
        if(EventDTOCreate.getDate_end()==null){
            return null;
        }
        return event.CreateEvent(EventDTOCreate);
    }

    @PutMapping("/update/{id}")
    public Event UpdateEvent(@PathVariable UUID id,@RequestBody EventDTOUpdate EventDTOUpdate) {
        return event.updateEvent(id,EventDTOUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public void DeleteEvent(@PathVariable UUID id) {
        event.deleteEvent(id);
    }

    @GetMapping("/getByFilter")
    public List<Event> searchEvents(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) UUID idCreator) {

        // Log to check received parameters
        System.out.println("Received parameters: startDate=" + startDate + ", location=" + location + ", idCreator=" + idCreator);

        EventFilterDTO criteria = new EventFilterDTO();
        if (startDate != null) {
            criteria.setDate_start(startDate.atStartOfDay());
        }
        criteria.setLocation(location);
        criteria.setIdCreator(idCreator);

        // Log to check criteria
        System.out.println("Search criteria: " + criteria);

        return event.searchEvents(criteria);
    }



    @GetMapping("/members/{memberId}")
    public Set<Event> getEventByMember (@PathVariable UUID memberId) throws UserNonExistent, RegistrationNonExistent {
        return event.getEventByMember(memberId);
    }

    @GetMapping("/non-registered/{memberId}")
    public List<Event> getEventsByNonRegisteredMember(@PathVariable UUID memberId) throws UserNonExistent, RegistrationNonExistent, EventNonExistant {
        return event.getEventByNonRegisteredMember(memberId);
    }



    }
