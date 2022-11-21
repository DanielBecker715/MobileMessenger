package com.darkvoidstudios.messenger.Domain.ports;

import com.darkvoidstudios.messenger.Domain.model.Message;

public interface MessageServicePort {
    Message sendMessage(Message message);
}
