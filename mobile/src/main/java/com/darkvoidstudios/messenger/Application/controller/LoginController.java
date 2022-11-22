package com.darkvoidstudios.messenger.Application.controller;

import com.darkvoidstudios.messenger.Application.model.UserDto;
import com.darkvoidstudios.messenger.Domain.EncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST)
    public UserDto login(@RequestBody String username, String unhashedPassword) {
        String loginUrl = "https://api.darkvoidstudios.com/messenger/login";

        //Preparing login data
        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        String hashedPassword = EncryptService.encryptSHA512(unhashedPassword);
        userDto.setHashedPassword(hashedPassword);

        //Sending login data to server
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", userDto.getUsername());
        map.add("hashedPassword", userDto.getHashedPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( loginUrl, request , String.class );

        //TODO TOKEN ABFANGEN
        userDto.setToken(null);

        return userDto;
    }
}