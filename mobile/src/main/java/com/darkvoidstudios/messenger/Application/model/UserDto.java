package com.darkvoidstudios.messenger.Application.model;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;
    private String hashedPassword;
    private String token;
}
