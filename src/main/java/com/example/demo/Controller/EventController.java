package com.example.demo.Controller;


import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.Event;
import com.example.demo.Services.EventServices;
import jdk.jfr.Category;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    private final EventServices service;

    public EventController(EventServices service) {
        this.service = service;
    }



}
