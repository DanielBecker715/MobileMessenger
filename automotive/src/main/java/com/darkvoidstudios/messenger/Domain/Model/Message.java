package com.darkvoidstudios.messenger.Domain.Model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class Message {
    private String uuid;
    private String message;
    private Timestamp messageDate;
}
