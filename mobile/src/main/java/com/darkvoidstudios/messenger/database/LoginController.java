package com.darkvoidstudios.messenger.database;

import com.darkvoidstudios.messenger.account.User;
import com.darkvoidstudios.messenger.crypto.EncryptService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginController {

    public void loginToAccount(String username, String unhashedPassword) throws IOException {
        URL url = new URL("https://api.darkvoidstudios.com/messenger/login");

        //Preparing login data
        User user = new User();
        user.setUsername(username);

        String hashedPassword = EncryptService.encryptSHA512(unhashedPassword);
        user.setHashedPassword(hashedPassword);

        System.out.println(user.getHashedPassword());

        //Preparing header
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        //Sending
        String jsonInputString = "{username: \""+user.getUsername()+"\", password: \""+user.getHashedPassword()+"\"}";

        byte[] out = jsonInputString.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = con.getOutputStream();
        stream.write(out);

        System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
        con.disconnect();

        //TODO TOKEN ABFANGEN
        user.setToken(null);
    }
}