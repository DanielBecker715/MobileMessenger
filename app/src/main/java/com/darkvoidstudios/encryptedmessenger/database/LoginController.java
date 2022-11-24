package com.darkvoidstudios.encryptedmessenger.database;

import com.darkvoidstudios.messenger.account.User;
import com.darkvoidstudios.messenger.crypto.EncryptService;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginController {

    public void loginToAccount(String username, String unhashedPassword) throws IOException, JSONException {
        URL url = new URL("https://api.darkvoidstudios.com/messenger/login/index.php");

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

        /*HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://api.darkvoidstudios.com/messenger/login");

        StringEntity params = new StringEntity(jsonUser.toString());
        request.addHeader("Content-Type", "application/json");
        request.setEntity(params);

        HttpResponse response = httpClient.execute(request);
        System.out.println("Code: " + response.getCode());*/

        //Test output
        System.out.println("Hash: " + user.getHashedPassword());
        System.out.println("JSON String: " + jsonUser);

        //TODO TOKEN ABFANGEN
        user.setToken(null);
    }
}