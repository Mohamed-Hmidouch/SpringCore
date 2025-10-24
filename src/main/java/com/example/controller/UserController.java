package com.example.controller;

import org.springframework.web.bind.annotation.*;

import com.example.service.UserService;
import com.example.model.User;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PatchMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
         
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        return userService.getUserByEmail(user.getEmail());
    }

    @GetMapping("/count")
    public Long countUsers(){
        return userService.countUsers();
    }
}