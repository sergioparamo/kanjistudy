package com.kanjistudy.views.loginProcess;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kanjistudy.R;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.Database;
import com.kanjistudy.database.dao.KanaDao;
import com.kanjistudy.database.dao.KanjiDao;
import com.kanjistudy.database.repo.KanaRepository;
import com.kanjistudy.database.repo.KanjiRepository;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.views.KanjiMenuActivity;
import com.kanjistudy.views.vocabularyViews.KanaActivity;

public class LandingActivity extends FragmentActivity {


    ToastsConfig toastsConfig = new ToastsConfig();
    TextView kanjiTextView, kanaTextView, hiraganaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kanjiTextView = findViewById(R.id.kanjiActivityTextViewMain);
        kanaTextView = findViewById(R.id.kanaActivityTextViewMain);
        hiraganaTextView = findViewById(R.id.hiraganaActivityTextViewMain);

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
