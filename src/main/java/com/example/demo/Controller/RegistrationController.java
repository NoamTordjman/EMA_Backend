package com.example.demo.Controller;


import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Registration;
import com.example.demo.Services.Impl.RegistrationServiceImpl;
import com.example.demo.Services.RegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private final RegistrationServices registrationService;

    public RegistrationController(RegistrationServices registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public Registration createRegistration(@RequestBody RegistrationDTOCreate registrationDTO) {
        return registrationService.CreateRegistration(registrationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRegistration(@PathVariable UUID id) {
        registrationService.deleteRegistration(id);
    }

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public Registration getRegistrationById(@PathVariable UUID id) {
        return registrationService.getRegistrationById(id);
    }
}