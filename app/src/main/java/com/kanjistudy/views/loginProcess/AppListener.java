package com.kanjistudy.views.loginProcess;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class AppListener implements TextWatcher {

    TextInputLayout textInputLayout;

    public AppListener(Context context, TextInputLayout givenTextInputLayout) {
        this.textInputLayout = givenTextInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        textInputLayout.setError(null);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        textInputLayout.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
