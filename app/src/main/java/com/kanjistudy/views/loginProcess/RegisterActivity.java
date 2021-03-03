package com.kanjistudy.views.loginProcess;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kanjistudy.R;
import com.kanjistudy.views.MainActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    MaterialButton loginButton, registerButton;
    MaterialCheckBox checkBox;

    TextInputLayout usernameInput, passwordInput, repeatInput, emailInput, nameInput, surnameInput, birthdateInput, genderInput;

    TextInputEditText usernameEditText, passwordEditText, repeatEditText, emailEditText, nameEditText, surnameEditText, birthdateEditText;

    private String[] genders = {"Male", "Female", "Others"};

    private AutoCompleteTextView genderDropDown;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        // FINDING VIEWS
        loginButton = findViewById(R.id.login_button_register);
        registerButton = findViewById(R.id.register_button_register);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        repeatInput = findViewById(R.id.repeatInput);
        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.nameInput);
        surnameInput = findViewById(R.id.surnameInput);
        birthdateInput = findViewById(R.id.birthdateInput);
        genderInput = findViewById(R.id.genderInput);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        repeatEditText = findViewById(R.id.repeatEditText);
        emailEditText = findViewById(R.id.emailEditText);
        nameEditText = findViewById(R.id.nameEditText);
        surnameEditText = findViewById(R.id.surnameEditText);
        birthdateEditText = findViewById(R.id.birthdateEditText);
        checkBox = findViewById(R.id.acceptCheckBox);
        genderDropDown = findViewById(R.id.genderAutoComplete);
        checkBox.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        birthdateEditText.setOnClickListener(this);
        birthdateEditText.setKeyListener(null);

        ArrayAdapter<String> genderListAdapter = new ArrayAdapter<String>(RegisterActivity.this, R.layout.gender_items_list, genders);
        genderDropDown.setAdapter(genderListAdapter);

        usernameEditText.addTextChangedListener(new AppListener(this, usernameInput));
        passwordEditText.addTextChangedListener(new AppListener(this, passwordInput));
        repeatEditText.addTextChangedListener(new AppListener(this, repeatInput));
        emailEditText.addTextChangedListener(new AppListener(this, emailInput));
        nameEditText.addTextChangedListener(new AppListener(this, nameInput));
        surnameEditText.addTextChangedListener(new AppListener(this, surnameInput));
        birthdateEditText.addTextChangedListener(new AppListener(this, birthdateInput));
        genderDropDown.addTextChangedListener(new AppListener(this, genderInput));


    }

    // ****** DIALOG *******
    public void showDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(RegisterActivity.this);
        builder.setTitle(R.string.dialogTitle);
        builder.setMessage(R.string.registrationDialog);
        builder.setPositiveButton(R.string.login_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //not needed i.putExtra("name", usernameEditText.getText().toString());
                startActivity(i);
            }
        });
        builder.show();
    }


    // **** DATE PICKER *****
    public void datePicker(String input) {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(input);
        final MaterialDatePicker<Long> picker = builder.build();
        picker.show(getSupportFragmentManager(), picker.toString());

        if (picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {

                birthdateEditText.setText(String.valueOf(picker.getHeaderText()));
            }
        })) ;


    }


    @Override
    public void onClick(View v) {

        String textInput;

        switch (v.getId()) {
            case R.id.login_button_register:
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.acceptCheckBox:
                checkBox.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.birthdateEditText:
                textInput = getString(R.string.dateTitle);
                datePicker(textInput);
                break;
            case R.id.register_button_register:
                if (usernameEditText.getText().toString().isEmpty() || passwordEditText.getText().length() < 8 || surnameEditText.getText().toString().isEmpty() || birthdateEditText.getText().toString().isEmpty() ||
                        !passwordEditText.getText().toString().equals(repeatEditText.getText().toString()) || emailEditText.getText().toString().isEmpty()
                        || nameEditText.getText().toString().isEmpty() || genderDropDown.getText().toString().isEmpty() || !checkBox.isChecked()) {

                    if (usernameEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.password_less_8);
                        usernameInput.setError(textInput);
                    }
                    if (passwordEditText.getText().length() < 8) {
                        textInput = getString(R.string.password_less_8);
                        passwordEditText.setError(textInput);
                    }
                    if (passwordEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.password_less_8);
                        passwordInput.setError(textInput);
                    }
                    if (repeatEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.empty);
                        repeatInput.setError(textInput);
                    }
                    if (!passwordEditText.getText().toString().equals(repeatEditText.getText().toString())) {
                        textInput = getString(R.string.passwords_no_match);
                        repeatInput.setError(textInput);
                    }
                    if (emailEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.empty);
                        emailInput.setError(textInput);
                    }
                    if (nameEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.empty);
                        nameInput.setError(textInput);
                    }
                    if (surnameEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.empty);
                        surnameInput.setError(textInput);
                    }
                    if (birthdateEditText.getText().toString().isEmpty()) {
                        textInput = getString(R.string.empty);
                        birthdateInput.setError(textInput);
                    }
                    if (genderDropDown.getText().toString().isEmpty()) {
                        textInput = getString(R.string.empty);
                        nameInput.setError(textInput);
                    }
                    if (!checkBox.isChecked()) {
                        textInput = getString(R.string.empty);
                        checkBox.setError(textInput);
                    }
                } else {
                    showDialog();
                }
                break;


        }


    }
}