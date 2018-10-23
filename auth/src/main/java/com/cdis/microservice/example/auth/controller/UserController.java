package com.cdis.microservice.example.auth.controller;

import com.cdis.microservice.example.auth.model.User;
import com.cdis.microservice.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(){
        return "HOME PAGE";
    }

    @GetMapping

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(User user){
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/usuarios")
    public List<User> getUsers(){
        return userService.findUsers();
    };

    @GetMapping("/usuarios/{id}")
    public User getUsersById(@PathVariable(name = "id") final Long id){
        return userService.findUserById(id);
    };

    @PostMapping("/usuarios")
    public User postUser(@Valid @RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/usuarios/{id}")
    public String deleteUsersById(@PathVariable(name = "id") final Long id){
        userService.deleteUsernameById(id);
        return "Usuario Borrado";
    };

    @GetMapping("/usuarios/{id}/send")
    public String sendUsersById(@PathVariable(name = "id") final Long id){
        return userService.sendUserCredentials(userService.findUserById(id).getUsername());
    };

}
