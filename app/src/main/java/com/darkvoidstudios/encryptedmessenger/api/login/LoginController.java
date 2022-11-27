package com.darkvoidstudios.encryptedmessenger.api.login;

import androidx.annotation.NonNull;

import com.darkvoidstudios.encryptedmessenger.models.User;
import com.darkvoidstudios.encryptedmessenger.crypto.EncryptService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginController {


    User user = new User();

    public User loginToAccount(String username, String unhashedPassword) throws IOException, JSONException {

        //Preparing post login data
        user.setUsername(username);
        String hashedPassword = EncryptService.encryptSHA512(unhashedPassword);
        user.setHashedPassword(hashedPassword);

        JSONObject jsonUserData = new JSONObject();
        jsonUserData.put("username", user.getUsername());
        jsonUserData.put("password", user.getHashedPassword());

        postUserData("https://api.darkvoidstudios.com/messenger/login/", jsonUserData.toString());
        return user;
    }

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private void postUserData(String url, String json) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        System.out.println("Sending JSON: " + json);

        Response response = client.newCall(request).execute();
        System.out.println("Received Response: " + response);
        try {
            JSONObject jsonResponse = new JSONObject(response.body().string());
            user.setToken(jsonResponse.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}