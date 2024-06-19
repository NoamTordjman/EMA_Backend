package com.example.demo.Services.Impl;

import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Event;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.RegistrationRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.User;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserAlreadyRegisteredException;
import com.example.demo.exception.UserNonExistent;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationServices {


    private final RegistrationRepository RegistrationRepository;
    private final UserRepository UserRepository;
    private final EventRepository EventRepository;

    public RegistrationServiceImpl(com.example.demo.Repository.RegistrationRepository registrationRepository, com.example.demo.Repository.UserRepository userRepository, com.example.demo.Repository.EventRepository eventRepository) {
        RegistrationRepository = registrationRepository;
        UserRepository = userRepository;
        EventRepository = eventRepository;
    }


    @Override
    public Registration CreateRegistration(RegistrationDTOCreate registrationDTO) {
        User user = UserRepository.findById(registrationDTO.getUserId()).orElseThrow(()-> new UserNonExistent("User not found with id: " + registrationDTO.getUserId()));
        Event event = EventRepository.findById(registrationDTO.getEventId()).orElseThrow(()-> new EventNonExistant("Event not found with id: " + registrationDTO.getEventId()));


        List<Registration> allRegistration = RegistrationRepository.findAll();
        for(Registration r : allRegistration){
            if((r.getEvent().getIdEvent() == event.getIdEvent()) && (r.getUser().getId() == user.getId())) {
                throw new UserAlreadyRegisteredException("User already registered");
            }
        }
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);
        return RegistrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(UUID id_Registration) {
        if (!RegistrationRepository.existsById(id_Registration)) {
            throw new RegistrationNonExistent("Registration not found with id: " + id_Registration);
        }
        RegistrationRepository.deleteById(id_Registration);
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return RegistrationRepository.findAll();
    }

    @Override
    public Registration getRegistrationById(UUID id_Registration) {
        return RegistrationRepository.findById(id_Registration).orElseThrow(()-> new RegistrationNonExistent("Registration not found with id: " + id_Registration));
    }
}
