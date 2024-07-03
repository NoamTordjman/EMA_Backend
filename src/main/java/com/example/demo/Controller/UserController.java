package com.example.demo.Controller;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import com.example.demo.exception.UserNonExistent;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name = "Users Controller API",
        description = "Permit to control the Users"
)
@RequestMapping("/v1/Users")
public class UserController {

    private final UserServices user;

    public UserController(UserServices user) {
        this.user = user;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTOCreate UserDTO) throws UserNonExistent {
        return ResponseEntity.ok(user.CreateUser(UserDTO));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(user.getAllUsers());
    }

    @GetMapping("/{id_user}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id_user) throws UserNonExistent {
        return ResponseEntity.ok(user.getUserById(id_user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserDTOUpdate UserDTOUpdate) throws UserNonExistent {
        return ResponseEntity.ok(user.updateUser(UserDTOUpdate));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) throws UserNonExistent {
        user.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/Login")
    public ResponseEntity<User> log(@RequestBody LoginRequest loginRequest) {
        User u = user.Login(loginRequest.getUsername(), loginRequest.getPassword());
        if (u != null) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.status(399).body(null);
        }
    }
}
