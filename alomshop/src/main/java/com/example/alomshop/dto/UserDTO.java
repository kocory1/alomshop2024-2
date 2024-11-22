package com.example.alomshop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private String username;
    private String password;
    private String role;
    private String email;
    private int age;

}
