package com.example.demo.Services;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Event;
import com.example.demo.Registration;

import java.util.List;
import java.util.UUID;

public interface RegistrationServices {

    Registration CreateRegistration(RegistrationDTOCreate registration);
    void deleteRegistration(UUID id_Registration);
    List<Registration> getAllRegistrations();
    Registration getRegistrationById(UUID id_Registration);



}
