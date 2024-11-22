package com.example.alomshop.service;

import com.example.alomshop.dto.UserDTO;
import com.example.alomshop.entity.User;
import com.example.alomshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser (UserDTO userDTO){

        String username = userDTO.getUsername();
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        String email = userDTO.getEmail();
        int age = userDTO.getAge();

        Boolean isExist = userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
        if (isExist) {
            return;
        }

        User userData = new User();
        userData.setUsername(username);
        userData.setPassword(encodedPassword);
        userData.setEmail(email);
        userData.setAge(age);
        userData.setRole("ROLE_USER");


        userRepository.save(userData);

    }

    public User getUser() {
        User user = new User();
        user = userRepository.findByUsername("admin");
        return user;
    }


}

