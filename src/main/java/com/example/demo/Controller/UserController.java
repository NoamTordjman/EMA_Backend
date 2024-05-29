package com.example.demo.Controller;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Event;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class UserController {

    private final UserServices user;
    public UserController(UserServices user) {
        this.user = user;
    }

    @PostMapping
    public User createUser(@RequestBody UserDTOCreate UserDTO) {
        return user.CreateUser(UserDTO);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return user.getAllUsers();
    }

    @PutMapping("/{id}")
    public User UpdateUser(@PathVariable UUID id, @RequestBody UserDTOUpdate UserDTOUpdate) {
        return user.updateUser(UserDTOUpdate);
    }

    @DeleteMapping
    public void DeleteUser(@PathVariable UUID id) {
        user.deleteUser(id);
    }
}
