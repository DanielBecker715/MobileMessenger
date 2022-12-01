package com.darkvoidstudios.encryptedmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.darkvoidstudios.encryptedmessenger.messages.MessageSerivce;
import com.darkvoidstudios.encryptedmessenger.models.Message;
import com.darkvoidstudios.encryptedmessenger.token.TokenController;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /** Called when the user touches the button */
    @SuppressLint("SetTextI18n")
    public void onLoginButtonSubmit(View view) throws IOException, JSONException {
        EditText inputUsername = findViewById(R.id.inputUsername);
        EditText inputPassword = findViewById(R.id.inputPassword);

        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        String token = TokenController.getToken(username, password);
        this.token = token;
        System.out.println("Token: "+token);

        if (token.isEmpty()) {
            TextView errorMessage = findViewById(R.id.errorMessage);
            errorMessage.setTextColor(Color.parseColor("#f44336"));
            errorMessage.setTypeface(null, Typeface.BOLD);
            errorMessage.setText("Incorrect username or passwort");
        } else {
            setContentView(R.layout.chatoverview);
        }
    }

    @SuppressLint("SetTextI18n")
    public void onOpenChat(View view) throws IOException, JSONException {
        setContentView(R.layout.chat1);
        var btnId = findViewById(view.getId());
        TextView contactName = findViewById(R.id.contactName);

        contactName.setText("Fabian");

        //TODO Add contacts and a local db to safe them
        /*TextView inputMessage = findViewById(R.id.contactName);
        TextView contactList = findViewById(R.id.fabian);
        inputMessage.setText(contactList.get);*/

        //TODO GET OLD Messages first
        ArrayList<Message> oldMessages = new ArrayList<>();

        MessageSerivce.getMessages(token, "fabian");
    }

    public void onMessageSubmit(View view) throws JSONException, IOException {
        TextView inputMessage = findViewById(R.id.inputMessage);
        String textMessage = inputMessage.getText().toString();

        if (!textMessage.isEmpty()) {
            inputMessage.setText(null);
            MessageSerivce.sendMessage(textMessage, "fabian", token);
        }
    }

    public void onBtnAddContacts(View view) {
        setContentView(R.layout.contacts);
    }

    public void onBtnBackToOverview(View view) {
        setContentView(R.layout.chatoverview);
    }

}