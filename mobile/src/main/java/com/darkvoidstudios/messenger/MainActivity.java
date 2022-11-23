package com.darkvoidstudios.messenger;

import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.darkvoidstudios.messenger.database.LoginController;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    LoginController loginController = new LoginController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /** Called when the user touches the button */
    public void onLoginButtonSubmit(View view) throws IOException {
        EditText inputUsername = findViewById(R.id.inputUsername);
        EditText inputPassword = findViewById(R.id.inputPassword);

        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        loginController.loginToAccount(username, password);
    }

}