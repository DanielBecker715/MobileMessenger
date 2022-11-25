package com.darkvoidstudios.encryptedmessenger.account;

import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private String hashedPassword;
    private String token;
}
