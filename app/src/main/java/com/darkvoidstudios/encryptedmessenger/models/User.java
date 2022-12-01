package com.darkvoidstudios.encryptedmessenger.models;

import androidx.room.Entity;

import lombok.Data;

@Entity
@Data
public class User {
    private String username;
    private String email;
    private String hashedPassword;
    private String token;
}
