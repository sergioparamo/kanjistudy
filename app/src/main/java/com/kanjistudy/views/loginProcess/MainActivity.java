package com.kanjistudy.views.loginProcess;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.kanjistudy.R;

import com.kanjistudy.views.loginProcess.LoginActivity;
import com.kanjistudy.views.loginProcess.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    MaterialButton loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);

        loginButton = findViewById(R.id.login_button_main);
        registerButton = findViewById(R.id.register_button_main);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}