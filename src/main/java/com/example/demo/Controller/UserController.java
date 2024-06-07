package com.example.demo.Controller;

import com.example.demo.DTO.EventDTOCreate;
import com.example.demo.DTO.EventDTOUpdate;
import com.example.demo.DTO.UserDTOCreate;
import com.example.demo.DTO.UserDTOUpdate;
import com.example.demo.Event;
import com.example.demo.Services.UserServices;
import com.example.demo.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@Tag(
        name="Users Controller API",
        description = "Permit to control the Users"
)
@RequestMapping("/v1/Users")
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

    @GetMapping("/Login/{mail}/{password}")
    public User Log(@PathVariable String mail,@PathVariable String password){
        return user.Login(mail,password);
    }
}
