package com.darkvoidstudios.messenger.Application.controller;

import com.darkvoidstudios.messenger.Application.model.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class MessageController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/sample/endpoint", method = RequestMethod.POST)
    public String sendMessage(@RequestBody MessageDto messageDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<MessageDto> entity = new HttpEntity<MessageDto>(messageDto,headers);

        return restTemplate.exchange(
                "https://example.com/endpoint", HttpMethod.POST, entity, String.class).getBody();
    }



}
