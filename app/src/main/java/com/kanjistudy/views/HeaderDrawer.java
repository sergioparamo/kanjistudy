package com.kanjistudy.views;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kanjistudy.R;
import com.kanjistudy.database.resources.Data;

public class HeaderDrawer extends AppCompatActivity {

    private TextView usernameNavDraw;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.header_drawer);

        usernameNavDraw = findViewById(R.id.username_header_drawer_id);

        do {
            System.out.println("NOT NAME YET");
        }while (Data.currentUser.getName() == null);

        usernameNavDraw.setText(Data.currentUser.getName());
    }
}
