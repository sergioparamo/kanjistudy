package com.kanjistudy.views.loginProcess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kanjistudy.R;

import com.kanjistudy.database.resources.Data;
import com.kanjistudy.views.KanjiMenuActivity;
import com.kanjistudy.views.quiz.QuizMenu;
import com.kanjistudy.views.vocabularyViews.KanaActivity;

public class MainActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;


    ActionBarDrawerToggle toggle;

    BottomNavigationView bottomNavigationView;
    String username;

    TextView kanjiTextView, kanaTextView, hiraganaTextView, welcomeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity);


        SharedPreferences sharedPreferences = getSharedPreferences("MyLogin.txt", Context.MODE_PRIVATE);
        Boolean loginCheck = sharedPreferences.getBoolean("FirstLogin", false);
        username = sharedPreferences.getString("username", null);

        if (!loginCheck) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            Data.loadData(getApplicationContext());
        }


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        kanjiTextView = findViewById(R.id.kanjiActivityTextViewMain);
        kanaTextView = findViewById(R.id.kanaActivityTextViewMain);
        hiraganaTextView = findViewById(R.id.hiraganaActivityTextViewMain);
        welcomeTextView = findViewById(R.id.welcomeTextViewMain);

        welcomeTextView.setText("ようこそ " + username + "!");


        setNavigationViewListener();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_bar_kanji:
                        Intent fromLandingToKanjiMenuIntentBottom = new Intent(MainActivity.this, KanjiMenuActivity.class);
                        startActivity(fromLandingToKanjiMenuIntentBottom);
                        break;
                    case R.id.bottom_bar_hiragana:
                        Intent fromLandingToHiraganaIntentBottom = new Intent(MainActivity.this, KanaActivity.class);
                        fromLandingToHiraganaIntentBottom.putExtra("type", "hiragana");
                        startActivity(fromLandingToHiraganaIntentBottom);
                        break;
                    case R.id.bottom_bar_katakana:
                        Intent fromLandingToKatakanaIntentBottom = new Intent(MainActivity.this, KanaActivity.class);
                        fromLandingToKatakanaIntentBottom.putExtra("type", "katakana");
                        startActivity(fromLandingToKatakanaIntentBottom);
                        break;
                    case R.id.bottom_bar_quiz:
                        Intent fromLandingToQuizMenuIntentBottom = new Intent(MainActivity.this, QuizMenu.class);
                        startActivity(fromLandingToQuizMenuIntentBottom);
                        break;
                }

                return false;
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.topAppBarMain);


        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homenavdraw:
                Intent fromLandingToHomeNavDraw = new Intent(MainActivity.this, MainActivity.class);
                startActivity(fromLandingToHomeNavDraw);
                break;
            case R.id.kanjinavdraw:
                Intent fromLandingToKanjiMenuIntentNavDraw = new Intent(MainActivity.this, KanjiMenuActivity.class);
                startActivity(fromLandingToKanjiMenuIntentNavDraw);
                break;
            case R.id.hiragananavdraw:
                Intent fromLandingToHiraganaIntentNavDraw = new Intent(MainActivity.this, KanaActivity.class);
                fromLandingToHiraganaIntentNavDraw.putExtra("type", "hiragana");
                startActivity(fromLandingToHiraganaIntentNavDraw);
                break;
            case R.id.katakananavdraw:
                Intent fromLandingToKatakanaIntentNavDraw = new Intent(MainActivity.this, KanaActivity.class);
                fromLandingToKatakanaIntentNavDraw.putExtra("type", "katakana");
                startActivity(fromLandingToKatakanaIntentNavDraw);
                break;
            case R.id.logoutnavdraw:
                break;
        }
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        navigationView.setNavigationItemSelectedListener(this);
    }
}