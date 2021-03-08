package com.kanjistudy.views.loginProcess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kanjistudy.R;
import com.kanjistudy.views.LandingActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton, registerButton;
    TextInputEditText username, password;
    TextInputLayout usernameInput, passwordInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        loginButton = findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(this);
        registerButton = findViewById(R.id.register_button_login);
        registerButton.setOnClickListener(this);

        username = findViewById(R.id.username_input_login);
        password = findViewById(R.id.password_input_login);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        username.addTextChangedListener(new AppListener(this, usernameInput));
        password.addTextChangedListener(new AppListener(this, passwordInput));

    }

    @Override
    public void onClick(View v) {
        String textInput;
        if (v.getId() == R.id.login_button_login) {

            if (username.getText().toString().isEmpty()) {

                textInput = getString(R.string.empty);
                username.setError(textInput);
            } else if (password.getText().length() < 8) {
                textInput = getString(R.string.password_less_8);
                password.setError(textInput);
            } else {
                Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
                startActivity(intent);
            }

        } else if (v.getId() == R.id.register_button_login) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }

    }
}
