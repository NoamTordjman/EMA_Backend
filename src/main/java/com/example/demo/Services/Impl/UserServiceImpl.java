package com.example.demo.Services.Impl;

import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User CreateUser(UserDTOCreate UserDTO) {
        User user = new User();
        user.setMail(UserDTO.getMail());
        user.setName(UserDTO.getName());
        user.setSurname(UserDTO.getSurname());
        return repository.save(user);
    }

    @Override
    public void deleteUser(UUID idUser) {
        repository.deleteById(idUser);
    }

    @Override
    public User updateUser(UserDTOUpdate userDTO) {
        User user = repository.findById(userDTO.getUserid()).orElse(null);
        assert user != null;
        user.setMail(userDTO.getMail());
        return repository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(UUID idUser) {
        return repository.findById(idUser).orElse(null);
    }

    @Override
    public User Login(String username, String password) {
        return repository.Login(username,password);
    }

}
