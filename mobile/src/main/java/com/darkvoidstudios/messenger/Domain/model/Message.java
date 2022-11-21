package com.darkvoidstudios.messenger.Domain.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Message {
    private String uuid;
    private String message;
    private Timestamp messageDate;
}
