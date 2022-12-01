package com.darkvoidstudios.encryptedmessenger.api;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

public class APIController {

    /**
     *
     * @return Returns the server response from the post request
     * @throws IOException
     */
    public static final Response postRequestViaTokenAuth(String url, String json, String token) throws IOException {
        final OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .post(body)
                .build();

        System.out.println("Sending JSON: " + json);
        Response response = client.newCall(request).execute();
        System.out.println("Received Response: " + response);
        return response;
    }

    /**
     *
     * @param url
     * @param username
     * @param hashedPassword
     * @return Returns the server response from the get request
     * @throws IOException
     */
    public static Response getRequestViaBasicAuth(final String url, final String username, final String hashedPassword) throws IOException {
        // Build client with authentication information.
        OkHttpClient httpClient = new OkHttpClient.Builder().authenticator(new Authenticator() {
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, hashedPassword);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();

        Request request = new Request.Builder().url(url).build();

        System.out.println("Sending get request to: " + url);
        Response response = httpClient.newCall(request).execute();
        System.out.println("Received Response: " + response);

        return response;
    }

    /**
     *
     * @param url
     * @param token
     * @return Returns the server response from the get request
     * @throws IOException
     */
    public static Response getRequestViaTokenAuth(final String url, final String token) throws IOException {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        System.out.println("Sending get request to: " + url);
        Response response = client.newCall(request).execute();
        System.out.println("Received Response: " + response);
        return response;
    }

    /**
     * Specialized Get request to only receive messages from a specific contact
     * @param url
     * @param token
     * @param chatContact
     * @return Returns the server response from the get request
     * @throws IOException
     */
    public static Response getMessageRequestViaTokenAuth(final String url, final String token, final String chatContact) throws IOException {
        final OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("chatContact", chatContact)
                .build();

        System.out.println("Sending get request to: " + url);
        Response response = client.newCall(request).execute();
        System.out.println("Received Response: " + response);
        return response;
    }
}
