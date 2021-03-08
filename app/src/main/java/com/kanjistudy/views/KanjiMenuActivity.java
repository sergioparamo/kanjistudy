package com.kanjistudy.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kanjistudy.R;
import com.kanjistudy.views.vocabularyViews.KanaActivity;
import com.kanjistudy.views.vocabularyViews.KanjiActivity;

public class KanjiMenuActivity extends FragmentActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;
    TextView level1, level2, level3, level4, level5, level6, level7;

    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_kanjioptions_activity);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_kanji_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_bar_home:
                        Intent fromKanjiMenuToLandingIntentBottom = new Intent(KanjiMenuActivity.this, LandingActivity.class);
                        startActivity(fromKanjiMenuToLandingIntentBottom);
                        break;
                    case R.id.bottom_bar_hiragana:
                        Intent fromKanjiMenuToHiraganaIntentBottom = new Intent(KanjiMenuActivity.this, KanaActivity.class);
                        fromKanjiMenuToHiraganaIntentBottom.putExtra("type", "hiragana");
                        startActivity(fromKanjiMenuToHiraganaIntentBottom);
                        break;
                    case R.id.bottom_bar_katakana:
                        Intent fromKanjiMenuToKatakanaIntentBottom = new Intent(KanjiMenuActivity.this, KanaActivity.class);
                        fromKanjiMenuToKatakanaIntentBottom.putExtra("type", "katakana");
                        startActivity(fromKanjiMenuToKatakanaIntentBottom);
                        break;
                }

                return false;
            }
        });


        level1 = findViewById(R.id.kanjiLevelOneSelectOptionsTextView);
        level2 = findViewById(R.id.kanjiLevelTwoSelectOptionsTextView);
        level3 = findViewById(R.id.kanjiLevelThreeSelectOptionsTextView);
        level4 = findViewById(R.id.kanjiLevelFourSelectOptionsTextView);
        level5 = findViewById(R.id.kanjiLevelFiveSelectOptionsTextView);
        level6 = findViewById(R.id.kanjiLevelSixSelectOptionsTextView);
        level7 = findViewById(R.id.kanjiLevelSevenSelectOptionsTextView);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_kanji_menu_layout);
        toolbar = findViewById(R.id.topAppBarKanjiMenu);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        navigationView = (NavigationView) findViewById(R.id.nav_view_kanjimenu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homenavdraw:
                        Intent fromKanjiMenuToLandingIntentNavDraw = new Intent(KanjiMenuActivity.this, LandingActivity.class);
                        startActivity(fromKanjiMenuToLandingIntentNavDraw);
                        break;
                    case R.id.kanjinavdraw:
                        Intent fromKanjiMenuToKanjiToKanjiMenuIntentNavDraw = new Intent(KanjiMenuActivity.this, KanjiMenuActivity.class);
                        startActivity(fromKanjiMenuToKanjiToKanjiMenuIntentNavDraw);
                        break;
                    case R.id.hiragananavdraw:
                        Intent fromKanjiMenuToHiraganaIntentNavDraw = new Intent(KanjiMenuActivity.this, KanaActivity.class);
                        fromKanjiMenuToHiraganaIntentNavDraw.putExtra("type", "hiragana");
                        startActivity(fromKanjiMenuToHiraganaIntentNavDraw);
                        break;
                    case R.id.katakananavdraw:
                        Intent fromKanjiMenuToKatakanaIntentNavDraw = new Intent(KanjiMenuActivity.this, KanaActivity.class);
                        fromKanjiMenuToKatakanaIntentNavDraw.putExtra("type", "katakana");
                        startActivity(fromKanjiMenuToKatakanaIntentNavDraw);
                        break;
                    case R.id.logoutnavdraw:
                        break;
                }


                return false;
            }
        });


        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 1;
                Intent fromKanjiOptionsToKanjiListLevel1 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel1.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel1);
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 2;
                Intent fromKanjiOptionsToKanjiListLevel2 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel2.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel2);
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 3;
                Intent fromKanjiOptionsToKanjiListLevel3 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel3.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel3);
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 4;
                Intent fromKanjiOptionsToKanjiListLevel4 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel4.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel4);
            }
        });

        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 5;
                Intent fromKanjiOptionsToKanjiListLevel5 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel5.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel5);
            }
        });

        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 6;
                Intent fromKanjiOptionsToKanjiListLevel6 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel6.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel6);
            }
        });

        level7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 7;
                Intent fromKanjiOptionsToKanjiListLevel7 = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToKanjiListLevel7.putExtra("level", level);
                startActivity(fromKanjiOptionsToKanjiListLevel7);
            }
        });


    }
}
