package com.darkvoidstudios.messenger.Application.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDto {
    private String uuid;
    private String message;
    private Timestamp messageDate;
}
