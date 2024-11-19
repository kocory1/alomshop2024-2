package com.example.alomshop.controller;

import com.example.alomshop.dto.UserDTO;
import com.example.alomshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping
//    public ResponseEntity<User> getUser(@RequestParam Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
