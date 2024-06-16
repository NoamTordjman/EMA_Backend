package com.example.demo.Controller;


import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Registration;
import com.example.demo.Services.Impl.RegistrationServiceImpl;
import com.example.demo.Services.RegistrationServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name="Registration Controller API",
        description = "Permit to control the Registration"
)
@RequestMapping("/v1/Registration")
public class RegistrationController {

    @Autowired
    private final RegistrationServices registrationService;

    public RegistrationController(RegistrationServices registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/Register")
    public ResponseEntity<Registration> createRegistration(@RequestBody RegistrationDTOCreate registrationDTO) {
        //Ajouter exeption sur la verif des 2 id ici
        return ResponseEntity.ok(registrationService.CreateRegistration(registrationDTO));
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