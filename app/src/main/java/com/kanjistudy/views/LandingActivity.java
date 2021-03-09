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
import com.google.android.material.navigation.NavigationView;
import com.kanjistudy.R;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.views.vocabularyViews.KanaActivity;

public class LandingActivity extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;


    ActionBarDrawerToggle toggle;

    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;

    ToastsConfig toastsConfig = new ToastsConfig();
    TextView kanjiTextView, kanaTextView, hiraganaTextView, welcomeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_activity);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        kanjiTextView = findViewById(R.id.kanjiActivityTextViewMain);
        kanaTextView = findViewById(R.id.kanaActivityTextViewMain);
        hiraganaTextView = findViewById(R.id.hiraganaActivityTextViewMain);
        welcomeTextView = findViewById(R.id.welcomeTextViewMain);

        welcomeTextView.setText("ようこそ " + Data.currentUser.getName() + "!");

        //navigationView = (NavigationView) findViewById(R.id.nav_view_main);
        setNavigationViewListener();


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

        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homenavdraw:
                        Intent fromLandingToHomeNavDraw = new Intent(LandingActivity.this, LandingActivity.class);
                        startActivity(fromLandingToHomeNavDraw);
                        break;
                    case R.id.kanjinavdraw:
                        Intent fromLandingToKanjiMenuIntentNavDraw = new Intent(LandingActivity.this, KanjiMenuActivity.class);
                        startActivity(fromLandingToKanjiMenuIntentNavDraw);
                        break;
                    case R.id.hiragananavdraw:
                        Intent fromLandingToHiraganaIntentNavDraw = new Intent(LandingActivity.this, KanaActivity.class);
                        fromLandingToHiraganaIntentNavDraw.putExtra("type", "hiragana");
                        startActivity(fromLandingToHiraganaIntentNavDraw);
                        break;
                    case R.id.katakananavdraw:
                        Intent fromLandingToKatakanaIntentNavDraw = new Intent(LandingActivity.this, KanaActivity.class);
                        fromLandingToKatakanaIntentNavDraw.putExtra("type", "katakana");
                        startActivity(fromLandingToKatakanaIntentNavDraw);
                        break;
                    case R.id.logoutnavdraw:
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });*/


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
                Intent fromLandingToHomeNavDraw = new Intent(LandingActivity.this, LandingActivity.class);
                startActivity(fromLandingToHomeNavDraw);
                break;
            case R.id.kanjinavdraw:
                Intent fromLandingToKanjiMenuIntentNavDraw = new Intent(LandingActivity.this, KanjiMenuActivity.class);
                startActivity(fromLandingToKanjiMenuIntentNavDraw);
                break;
            case R.id.hiragananavdraw:
                Intent fromLandingToHiraganaIntentNavDraw = new Intent(LandingActivity.this, KanaActivity.class);
                fromLandingToHiraganaIntentNavDraw.putExtra("type", "hiragana");
                startActivity(fromLandingToHiraganaIntentNavDraw);
                break;
            case R.id.katakananavdraw:
                Intent fromLandingToKatakanaIntentNavDraw = new Intent(LandingActivity.this, KanaActivity.class);
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
