package com.kanjistudy.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.kanjistudy.R;
import com.kanjistudy.views.vocabularyViews.KanjiActivity;

public class KanjiMenuActivity extends FragmentActivity {

    TextView level1, level2, level3, level4, level5, level6, level7, allKanjis;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_kanji_levels_activity);

        level1 = findViewById(R.id.kanjiLevelOneSelectOptionsTextView);
        level2 = findViewById(R.id.kanjiLevelTwoSelectOptionsTextView);
        level3 = findViewById(R.id.kanjiLevelThreeSelectOptionsTextView);
        level4 = findViewById(R.id.kanjiLevelFourSelectOptionsTextView);
        level5 = findViewById(R.id.kanjiLevelFiveSelectOptionsTextView);
        level6 = findViewById(R.id.kanjiLevelSixSelectOptionsTextView);
        level7 = findViewById(R.id.kanjiLevelSevenSelectOptionsTextView);
        allKanjis = findViewById(R.id.allKanjiSelectOptionsTextView);


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

        allKanjis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pasar a la otra con un bundle
                int level = 8;
                Intent fromKanjiOptionsToAllKanjis = new Intent(getApplicationContext(), KanjiActivity.class);
                fromKanjiOptionsToAllKanjis.putExtra("level", level);
                startActivity(fromKanjiOptionsToAllKanjis);
            }
        });


    }
}
