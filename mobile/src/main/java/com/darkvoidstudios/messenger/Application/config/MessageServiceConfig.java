package com.darkvoidstudios.messenger.Application.config;

import com.darkvoidstudios.messenger.Domain.MessageService;
import org.springframework.context.annotation.Bean;

public class MessageServiceConfig {
    @Bean
    public MessageService getMessageService() {
        return new MessageService();
    }
}
