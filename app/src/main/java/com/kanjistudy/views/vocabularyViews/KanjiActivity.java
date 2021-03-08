package com.kanjistudy.views.vocabularyViews;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kanjistudy.R;
import com.kanjistudy.controllers.KanjiAdapter;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.models.KanjiDb;
import com.kanjistudy.views.KanjiMenuActivity;
import com.kanjistudy.views.LandingActivity;

import java.util.List;

public class KanjiActivity extends FragmentActivity {

    private RecyclerView recyclerViewMain;
    private KanjiAdapter kanjiAdapter;
    private List<KanjiDb> localKanjiList;

    private Button lastLevel, nextLevel;
    ActionBarDrawerToggle toggle;

    public TextView currentLevelTextView;

    public static int levelIndex;

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_kanji_activity);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_kanji);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_bar_home:
                        Intent fromKanjiToLandingIntentBottom = new Intent(KanjiActivity.this, LandingActivity.class);
                        startActivity(fromKanjiToLandingIntentBottom);
                        break;
                    case R.id.bottom_bar_kanji:
                        Intent fromKanjiToKanjiMenuIntentBottom = new Intent(KanjiActivity.this, KanjiMenuActivity.class);
                        startActivity(fromKanjiToKanjiMenuIntentBottom);
                        break;
                    case R.id.bottom_bar_hiragana:
                        Intent fromKanjiToHiraganaIntentBottom = new Intent(KanjiActivity.this, KanaActivity.class);
                        fromKanjiToHiraganaIntentBottom.putExtra("type", "hiragana");
                        startActivity(fromKanjiToHiraganaIntentBottom);
                        break;
                    case R.id.bottom_bar_katakana:
                        Intent fromKanjiToKatakanaIntentBottom = new Intent(KanjiActivity.this, KanaActivity.class);
                        fromKanjiToKatakanaIntentBottom.putExtra("type", "katakana");
                        startActivity(fromKanjiToKatakanaIntentBottom);
                        break;
                }

                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerkanji_layout);
        toolbar = findViewById(R.id.topAppBarKanji);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            levelIndex = bundle.getInt("level");
        }


        lastLevel = findViewById(R.id.previous_level_button);
        nextLevel = findViewById(R.id.next_level_button);
        currentLevelTextView = findViewById(R.id.subTitleTextViewMain);
        currentLevelTextView.setText("Level " + levelIndex);

        if (levelIndex == 1) {
            lastLevel.setVisibility(View.INVISIBLE);
        } else if (levelIndex == 7) {
            nextLevel.setVisibility(View.INVISIBLE);
        }


        //toastsConfig.showToastByDuration(getApplicationContext(), 2, Integer.toString(levelIndex));

        kanjiAdapter = new KanjiAdapter(localKanjiList);

        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        recyclerViewMain.setAdapter(kanjiAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMain.setLayoutManager(layoutManager);

        //callApi("https://kanjiapi.dev/v1/kanji/grade-1");

        if (levelIndex == 8) {
            localKanjiList = Data.kanjiRepository.getKanjisByLevel(8);
        } else {
            localKanjiList = Data.kanjiRepository.getKanjisByLevel(levelIndex);
        }

        kanjiAdapter.setKanjis(localKanjiList);

        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                levelIndex++;
                //toastsConfig.showToastByDuration(getApplicationContext(), 2, Integer.toString(levelIndex));

                if (levelIndex == 7) {
                    nextLevel.setVisibility(View.INVISIBLE);
                } else {
                    //Making visible the previous level button
                    lastLevel.setVisibility(View.VISIBLE);
                }
                //Loading the previous kanji dataset
                localKanjiList = Data.kanjiRepository.getKanjisByLevel(levelIndex);
                kanjiAdapter.setKanjis(localKanjiList);

                currentLevelTextView.setText("Level " + levelIndex);

            }
        });

        lastLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                levelIndex--;
                //toastsConfig.showToastByDuration(getApplicationContext(), 2, Integer.toString(levelIndex));

                if (levelIndex == 1) {
                    lastLevel.setVisibility(View.INVISIBLE);
                } else {
                    //Making visible the next level button
                    nextLevel.setVisibility(View.VISIBLE);
                }

                //Loading the next kanji dataset
                localKanjiList = Data.kanjiRepository.getKanjisByLevel(levelIndex);
                kanjiAdapter.setKanjis(localKanjiList);
                currentLevelTextView.setText("Level " + levelIndex);
            }
        });
    }


}
