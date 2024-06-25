package com.example.demo.Controller;


import com.example.demo.DTO.RegistrationDTOCreate;
import com.example.demo.Registration;
import com.example.demo.Services.Impl.RegistrationServiceImpl;
import com.example.demo.Services.RegistrationServices;
import com.example.demo.exception.EventNonExistant;
import com.example.demo.exception.RegistrationNonExistent;
import com.example.demo.exception.UserAlreadyRegisteredException;
import com.example.demo.exception.UserNonExistent;
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

    @PostMapping("/create")
    public ResponseEntity<Registration> createRegistration(@RequestBody RegistrationDTOCreate registrationDTO) throws UserAlreadyRegisteredException,UserAlreadyRegisteredException,UserNonExistent,EventNonExistant {
        Registration reg = registrationService.CreateRegistration(registrationDTO);
        return ResponseEntity.ok(reg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteRegistration(@PathVariable UUID id) throws RegistrationNonExistent {
        registrationService.deleteRegistration(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping(("/getall"))
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable UUID id) throws RegistrationNonExistent {
        Registration reg= registrationService.getRegistrationById(id);
        return ResponseEntity.ok(reg);
    }

    @GetMapping(("/getallbyuserid/{id_user}"))
    public List<Registration> getAllRegistrations(@PathVariable UUID id_user) throws UserNonExistent {
        return registrationService.getRegistrationByUserId(id_user);
    }

}