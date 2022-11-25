package com.darkvoidstudios.encryptedmessenger;

import androidx.appcompat.app.AppCompatActivity;

import com.darkvoidstudios.encryptedmessenger.database.LoginController;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    LoginController loginController = new LoginController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user touches the button */
    public void onLoginButtonSubmit(View view) throws IOException, JSONException {
        EditText inputUsername = findViewById(R.id.inputUsername);
        EditText inputPassword = findViewById(R.id.inputPassword);

        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        loginController.loginToAccount(username, password);
    }

}