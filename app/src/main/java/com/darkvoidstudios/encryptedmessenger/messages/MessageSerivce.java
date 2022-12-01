package com.darkvoidstudios.encryptedmessenger.messages;

import com.darkvoidstudios.encryptedmessenger.api.APIController;
import com.darkvoidstudios.encryptedmessenger.models.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class MessageSerivce {

    public static String messageUrl = "https://api.darkvoidstudios.com/messenger/message/";

    /**
     * Sends a message
     * @param message The message
     * @param username This is the user to whom the message will be sent
     * @param userToken This must be the user token from the message sender
     * @throws IOException
     * @throws JSONException
     */
    public static void sendMessage(String message, String username, String userToken) throws IOException, JSONException {
        JSONObject jsonUserData = new JSONObject();
        jsonUserData.put("message", message);
        jsonUserData.put("textreceiver", username);

        System.out.println("Sending token: " + userToken);

        Response response = APIController.postRequestViaTokenAuth("https://api.darkvoidstudios.com/messenger/message/", jsonUserData.toString(), userToken);
        JSONObject jsonResponse = new JSONObject(response.body().string());
        System.out.println(jsonResponse.getString("message"));
    }

    /**
     *
     * @param token
     * @return Returns a ArrayList with all queued messages
     * @throws IOException
     * @throws JSONException
     */
    public static final ArrayList<Message> getMessages(String token, String chatContact) throws IOException, JSONException {
        Response response = APIController.getMessageRequestViaTokenAuth(messageUrl, token, chatContact);
        System.out.println(response.body().string());
        //TODO Messages in eine Arraylist packen
        return new ArrayList<>();
    }
}
