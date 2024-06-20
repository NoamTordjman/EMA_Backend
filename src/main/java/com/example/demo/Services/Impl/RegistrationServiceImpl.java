package com.example.demo.Services.Impl;

import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Event;
import com.example.demo.Registration;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Repository.RegistrationRepository;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserAlreadyRegisteredException;
import com.example.demo.exception.UserNonExistent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationServices {


    private final RegistrationRepository RegistrationRepository;
    private final UserServices UserService;
    private final EventRepository EventRepository;

    public RegistrationServiceImpl(com.example.demo.Repository.RegistrationRepository registrationRepository, UserServices userService, EventRepository eventRepository) {
        RegistrationRepository = registrationRepository;
        UserService = userService;
        EventRepository = eventRepository;
    }



    @Override
    public List<Registration> getRegistrationByUserId(UUID id_user) throws UserNonExistent {
        User u = UserService.getUserById(id_user);
        return RegistrationRepository.findByUser(u);
    }




    @Override
    public Registration CreateRegistration(RegistrationDTOCreate registrationDTO) throws UserAlreadyRegisteredException,UserNonExistent,EventNonExistant{
        User user = UserService.getUserById(registrationDTO.getUserId());
        Event event = EventRepository.findById(registrationDTO.getEventId()).orElseThrow(()->new EventNonExistant(registrationDTO.getUserId()));

        List<Registration> allRegistration = RegistrationRepository.findAll();
        for(Registration r : allRegistration){
            if((r.getEvent().getIdEvent() == event.getIdEvent()) && (r.getUser().getId() == user.getId())) {
                throw new UserAlreadyRegisteredException(user.getId());
            }
        }
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(user);
        return RegistrationRepository.save(registration);
    }

    @Override
    public void deleteRegistration(UUID id_Registration) throws EventNonExistant {
        getRegistrationById(id_Registration);
        RegistrationRepository.deleteById(id_Registration);
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return RegistrationRepository.findAll();
    }

    @Override
    public Registration getRegistrationById(UUID id_Registration) throws EventNonExistant {
        return RegistrationRepository.findById(id_Registration).orElseThrow(()-> new RegistrationNonExistent(id_Registration));
    }
}
