package com.example.demo.Services;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Event;
import com.example.demo.User;

import java.util.List;
import java.util.UUID;

public interface UserServices {

    User CreateUser(UserDTOCreate User);
    void deleteUser(UUID idUser);
    User updateUser(UserDTOUpdate user);
    List<User> getAllUsers();
    Event getUserById(UUID idUser);
}
