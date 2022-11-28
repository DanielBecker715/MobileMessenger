package com.darkvoidstudios.encryptedmessenger.models;

import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private String hashedPassword;
    private String token;
}
