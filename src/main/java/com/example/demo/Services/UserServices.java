package com.example.demo.Services;

import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.User;
import com.example.demo.exception.UserNonExistent;

import java.util.List;
import java.util.UUID;

public interface UserServices {

    User CreateUser(UserDTOCreate User) throws UserNonExistent;
    void deleteUser(UUID idUser) throws UserNonExistent;
    User updateUser(UserDTOUpdate user) throws UserNonExistent;
    List<User> getAllUsers();
    User getUserById(UUID idUser) throws UserNonExistent;
    User Login(String username, String password);
}
