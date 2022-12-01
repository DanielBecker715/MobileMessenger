package com.darkvoidstudios.encryptedmessenger.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Message {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private String uuid;
    @ColumnInfo
    private String message;
    @ColumnInfo
    private Timestamp messageDate;
}
