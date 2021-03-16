package com.kanjistudy.views.quiz;

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
import com.kanjistudy.R;
import com.kanjistudy.views.KanjiMenuActivity;
import com.kanjistudy.views.loginProcess.MainActivity;
import com.kanjistudy.views.vocabularyViews.KanaActivity;

public class QuizMenu extends FragmentActivity {

    BottomNavigationView bottomNavigationView;

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;
    ActionBarDrawerToggle toggle;

    TextView subtitleQuizMenu, kanjiQuizTextView, hiraganaQuizTextView, katakanaQuizTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_quiz_menu_layout);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_quiz_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_bar_home:
                        Intent fromQuizMenuToLandingIntentBottom = new Intent(QuizMenu.this, MainActivity.class);
                        startActivity(fromQuizMenuToLandingIntentBottom);
                        break;
                    case R.id.bottom_bar_kanji:
                        Intent fromQuizMenuToKanjiIntentBottom = new Intent(QuizMenu.this, KanjiMenuActivity.class);
                        startActivity(fromQuizMenuToKanjiIntentBottom);
                        break;
                    case R.id.bottom_bar_hiragana:
                        Intent fromQuizToHiraganaIntentBottom = new Intent(QuizMenu.this, KanaActivity.class);
                        fromQuizToHiraganaIntentBottom.putExtra("type", "hiragana");
                        startActivity(fromQuizToHiraganaIntentBottom);
                        break;
                    case R.id.bottom_bar_katakana:
                        Intent fromQuizToKatakanaIntentBottom = new Intent(QuizMenu.this, KanaActivity.class);
                        fromQuizToKatakanaIntentBottom.putExtra("type", "katakana");
                        startActivity(fromQuizToKatakanaIntentBottom);
                        break;
                }

                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_quiz_menu);
        toolbar = findViewById(R.id.topAppBarQuizMenu);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        subtitleQuizMenu = findViewById(R.id.subtitleTextViewQuizMenu);
        kanjiQuizTextView = findViewById(R.id.kanjiQuizTextView);
        hiraganaQuizTextView = findViewById(R.id.hiraganaQuizTextView);
        katakanaQuizTextView = findViewById(R.id.katakanaQuizTextView);

        subtitleQuizMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizMenu.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        kanjiQuizTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizMenu.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        hiraganaQuizTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizMenu.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        katakanaQuizTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizMenu.this, QuizActivity.class);
                startActivity(intent);
            }
        });


    }
}
