package com.kanjistudy.controllers;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastsConfig {

    //This method will display a given toast during "x" given seconds which will be converted into miliseconds
    //Copyright Sergio Paramo
    public void showToastByDuration(Context context, int seconds, final String toastText) {


        int miliseconds = seconds * 1000;

        final Toast toast = Toast.makeText(context, toastText,
                Toast.LENGTH_LONG);

        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, miliseconds);

    }

}