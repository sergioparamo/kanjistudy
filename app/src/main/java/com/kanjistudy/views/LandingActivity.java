package com.kanjistudy.views;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.kanjistudy.R;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.views.vocabularyViews.KanaActivity;

public class LandingActivity extends FragmentActivity {


    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;

    ToastsConfig toastsConfig = new ToastsConfig();
    TextView kanjiTextView, kanaTextView, hiraganaTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity);


        kanjiTextView = findViewById(R.id.kanjiActivityTextViewMain);
        kanaTextView = findViewById(R.id.kanaActivityTextViewMain);
        hiraganaTextView = findViewById(R.id.hiraganaActivityTextViewMain);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.topAppBarMain);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );


        Data.loadData(getApplicationContext());


        kanjiTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fromMainToKanjiOptionsActivity = new Intent(getApplicationContext(), KanjiMenuActivity.class);
                startActivity(fromMainToKanjiOptionsActivity);
            }
        });

        hiraganaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fromMainToHiraganaActivity = new Intent(getApplicationContext(), KanaActivity.class);
                fromMainToHiraganaActivity.putExtra("type", "hiragana");
                startActivity(fromMainToHiraganaActivity);
            }
        });


        kanaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fromMainToKatakanaActivity = new Intent(getApplicationContext(), KanaActivity.class);
                fromMainToKatakanaActivity.putExtra("type", "katakana");
                startActivity(fromMainToKatakanaActivity);
            }
        });


    }

}
