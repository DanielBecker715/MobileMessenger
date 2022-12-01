package com.darkvoidstudios.encryptedmessenger.token;

import com.darkvoidstudios.encryptedmessenger.api.APIController;
import com.darkvoidstudios.encryptedmessenger.models.User;
import com.darkvoidstudios.encryptedmessenger.crypto.EncryptService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

public class TokenController {

    public static String tokenUrl = "https://api.darkvoidstudios.com/messenger/token/";

    /**
     *
     * @param username
     * @param unhashedPassword
     * @return Returns the a server generated token
     */
    public static String getToken(String username, String unhashedPassword) throws IOException, JSONException {
        String hashedPassword = EncryptService.encryptSHA512(unhashedPassword);

        Response response = APIController.getRequestViaBasicAuth(tokenUrl, username, hashedPassword);

        JSONObject jsonResponse = new JSONObject(response.body().string());
        return jsonResponse.getString("token");
    }
}