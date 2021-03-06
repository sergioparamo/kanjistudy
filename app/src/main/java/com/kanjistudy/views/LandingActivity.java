package com.kanjistudy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kanjistudy.R;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.views.vocabularyViews.KanaActivity;

public class LandingActivity extends FragmentActivity {


    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;

    BottomNavigationView bottomNavigationView;

    ToastsConfig toastsConfig = new ToastsConfig();
    TextView kanjiTextView, kanaTextView, hiraganaTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_bar_kanji:
                        Intent fromLandingToKanjiMenuIntentBottom = new Intent(LandingActivity.this, KanjiMenuActivity.class);
                        startActivity(fromLandingToKanjiMenuIntentBottom);
                        break;
                    case R.id.bottom_bar_hiragana:
                        Intent fromLandingToHiraganaIntentBottom = new Intent(LandingActivity.this, KanaActivity.class);
                        fromLandingToHiraganaIntentBottom.putExtra("type", "hiragana");
                        startActivity(fromLandingToHiraganaIntentBottom);
                        break;
                    case R.id.bottom_bar_katakana:
                        Intent fromLandingToKatakanaIntentBottom = new Intent(LandingActivity.this, KanaActivity.class);
                        fromLandingToKatakanaIntentBottom.putExtra("type", "katakana");
                        startActivity(fromLandingToKatakanaIntentBottom);
                        break;
                }

                return false;
            }
        });

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
