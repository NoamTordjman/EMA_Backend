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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@Tag(
        name = "Event Controller API",
        description = "Permit to control the Event"
)
@RequestMapping("/v1/Event")
public class EventController {

    private final EventServices event;

    @Autowired
    public EventController(EventServices event) {
        this.event = event;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(event.getAllEvents());
    }

    @GetMapping("/getbyidcreator/{id}")
    public ResponseEntity<List<Event>> getEventsByIdCreator(@PathVariable UUID id) {
        return ResponseEntity.ok(event.getEventByIdCreator(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTOCreate eventDTOCreate) {
        if (eventDTOCreate.getDate_end() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(event.CreateEvent(eventDTOCreate));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, @RequestBody EventDTOUpdate eventDTOUpdate) {
        return ResponseEntity.ok(event.updateEvent(id, eventDTOUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        event.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByFilter")
    public ResponseEntity<List<Event>> searchEvents(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) UUID idCreator) {

        EventFilterDTO criteria = new EventFilterDTO();
        if (startDate != null) {
            criteria.setDate_start(startDate.atStartOfDay());
        }
        criteria.setLocation(location);
        criteria.setIdCreator(idCreator);

        return ResponseEntity.ok(event.searchEvents(criteria));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<Set<Event>> getEventByMember(@PathVariable UUID memberId) throws UserNonExistent, RegistrationNonExistent {
        return ResponseEntity.ok(event.getEventByMember(memberId));
    }

    @GetMapping("/non-registered/{memberId}")
    public ResponseEntity<List<Event>> getEventsByNonRegisteredMember(@PathVariable UUID memberId) throws UserNonExistent, RegistrationNonExistent, EventNonExistant {
        return ResponseEntity.ok(event.getEventByNonRegisteredMember(memberId));
    }
}
