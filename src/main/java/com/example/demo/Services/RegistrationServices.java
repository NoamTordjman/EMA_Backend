package com.example.demo.Services;

import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Registration;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserAlreadyRegisteredException;
import com.example.demo.exception.UserNonExistent;

import java.util.List;
import java.util.UUID;

public interface RegistrationServices {

    List<Registration> getRegistrationByUserId(UUID id_user) throws EventNonExistant;

    Registration CreateRegistration(RegistrationDTOCreate registration) throws UserAlreadyRegisteredException,UserNonExistent,EventNonExistant;
    void deleteRegistration(UUID id_Registration) throws RegistrationNonExistent;
    List<Registration> getAllRegistrations();
    Registration getRegistrationById(UUID id_Registration) throws RegistrationNonExistent;



}
