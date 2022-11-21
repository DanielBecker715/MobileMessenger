package com.darkvoidstudios.messenger.Domain;

import com.darkvoidstudios.messenger.Domain.model.Message;
import com.darkvoidstudios.messenger.Domain.ports.MessageServicePort;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MessageService {

    MessageServicePort messageServicePort;

    public void sendMessage(String message, String receiverUsername) {

    }

    public Message receiveMessage(Message message) {
        return message;
    }
}
