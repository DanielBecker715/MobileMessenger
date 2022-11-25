package com.darkvoidstudios.encryptedmessenger.database;

import com.darkvoidstudios.encryptedmessenger.account.User;
import com.darkvoidstudios.encryptedmessenger.crypto.EncryptService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginController {

    public void loginToAccount(String username, String unhashedPassword) throws IOException, JSONException {
        URL url = new URL("https://api.darkvoidstudios.com/messenger/login");

        //Preparing login data
        User user = new User();
        user.setUsername(username);

        String hashedPassword = EncryptService.encryptSHA512(unhashedPassword);
        user.setHashedPassword(hashedPassword);

        //Creating user in json format
        JSONObject jsonUser = new JSONObject();
        //jsonUser.put("username", user.getUsername());
        //jsonUser.put("password", user.getHashedPassword());

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        jsonUser.put("username", user.getUsername());
        jsonUser.put("password", user.getHashedPassword());

        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
        wr.write(jsonUser.toString());





        //TODO Sending via POST Method

       /* URL url = new URL("http://www.android.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }

/*
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
*/
        //TODO TOKEN ABFANGEN
        user.setToken(null);
    }
}