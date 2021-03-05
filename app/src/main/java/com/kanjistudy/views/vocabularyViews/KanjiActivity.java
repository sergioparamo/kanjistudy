package com.kanjistudy.views.vocabularyViews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.kanjistudy.R;
import com.kanjistudy.controllers.KanjiAdapter;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.models.KanjiDb;

import java.util.List;

public class KanjiActivity extends FragmentActivity {

    private RecyclerView recyclerViewMain;
    private KanjiAdapter kanjiAdapter;
    private List<KanjiDb> localKanjiList;

    private Button lastLevel, nextLevel;


    public TextView currentLevelTextView;

    public static int levelIndex;

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_kanji_activity);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerkanji_layout);
        toolbar = findViewById(R.id.topAppBarKanji);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

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
