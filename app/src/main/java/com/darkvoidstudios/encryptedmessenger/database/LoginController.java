package com.darkvoidstudios.encryptedmessenger.database;

import com.darkvoidstudios.encryptedmessenger.account.User;
import com.darkvoidstudios.encryptedmessenger.crypto.EncryptService;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginController {

    public void loginToAccount(String username, String unhashedPassword) throws IOException, JSONException {
        String postEndpoint = "https://api.darkvoidstudios.com/messenger/login";

        //Preparing login data
        User user = new User();
        user.setUsername(username);

        String hashedPassword = EncryptService.encryptSHA512(unhashedPassword);
        user.setHashedPassword(hashedPassword);

        //Creating user in json format
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", user.getUsername());
        jsonUser.put("password", user.getHashedPassword());

        //TODO Sending via POST Method

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost(postEndpoint);

        StringEntity params = new StringEntity(jsonUser.toString());
        request.addHeader("Content-Type", "application/json");
        request.setEntity(params);

        HttpResponse response = httpClient.execute(request);
        System.out.println("Code: " + response.getStatusLine());

        //Test output
        System.out.println("Hash: " + user.getHashedPassword());
        System.out.println("JSON String: " + jsonUser);

        //TODO TOKEN ABFANGEN
        user.setToken(null);
    }
}