package com.example.alomshop.controller;

import com.example.alomshop.dto.UserDTO;
import com.example.alomshop.entity.User;
import com.example.alomshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return "good";
    }

    @GetMapping()
    public User adminP() {
        return userService.getUser();
    }
}
