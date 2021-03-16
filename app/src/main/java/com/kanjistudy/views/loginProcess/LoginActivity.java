package com.kanjistudy.views.loginProcess;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kanjistudy.R;
import com.kanjistudy.database.resources.Data;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton, registerButton;
    TextInputEditText username, password;
    TextInputLayout usernameInput, passwordInput;


    public static final String PREF_LOGIN = "LOGIN_PREF";
    public static final String KEY_CREDENTIALS = "LOGIN_CREDENTIALS";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        if (Data.userRepository == null) {
            Data.createUserRepo(getApplicationContext());
        }

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

                boolean isUserOnDB = Data.checkUserName(username.getText().toString());

                if (isUserOnDB) {
                    boolean isPasswordCorrect = Data.checkPassword(username.getText().toString(), password.getText().toString());
                    if (isPasswordCorrect) {

                        SharedPreferences sharedPreferences = getSharedPreferences("MyLogin.txt", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("FirstLogin", true);

                        editor.putString("username", username.getText().toString());

                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);


                    } else {
                        passwordInput.setError(getString(R.string.wrong_password));
                        SharedPreferences.Editor editor = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE).edit();
                        editor.clear();
                        editor.commit();
                    }
                } else {
                    usernameInput.setError(getString(R.string.wrong_username));
                    SharedPreferences.Editor editor = getSharedPreferences(PREF_LOGIN, MODE_PRIVATE).edit();
                    editor.clear();
                    editor.commit();
                }

            }

        } else if (v.getId() == R.id.register_button_login) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }

    }
}
