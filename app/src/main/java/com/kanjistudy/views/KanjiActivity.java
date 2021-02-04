package com.kanjistudy.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kanjistudy.R;
import com.kanjistudy.controllers.LocalDBAdapter;
import com.kanjistudy.database.KanjiDao;
import com.kanjistudy.database.KanjiDatabase;
import com.kanjistudy.database.KanjiRepository;
import com.kanjistudy.models.Kanji;
import com.kanjistudy.models.KanjiDb;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class KanjiActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMain;
    private LocalDBAdapter localDBAdapter;
    private List<KanjiDb> localKanjiList;
    private Retrofit retrofit;
    private HttpLoggingInterceptor interceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Button lastLevel, nextLevel;

    private String kanji;
    public TextView currentLevelTextView;

    public static int levelIndex;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanji_activity);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            levelIndex = bundle.getInt("level");
        }


        lastLevel = findViewById(R.id.previous_level_button);
        nextLevel = findViewById(R.id.next_level_button);
        currentLevelTextView = findViewById(R.id.subTitleTextViewMain);
        currentLevelTextView.setText("Level " + levelIndex);

        if (levelIndex == 1){
            lastLevel.setVisibility(View.INVISIBLE);
        }else if (levelIndex == 6){
            nextLevel.setVisibility(View.INVISIBLE);
        }


        //toastsConfig.showToastByDuration(getApplicationContext(), 2, Integer.toString(levelIndex));

        localDBAdapter = new LocalDBAdapter(localKanjiList);

        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        recyclerViewMain.setAdapter(localDBAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewMain.setLayoutManager(layoutManager);

        //callApi("https://kanjiapi.dev/v1/kanji/grade-1");

        //kanjiDatabase = KanjiDatabase.getInstance(this.getApplicationContext());
       // kanjiDao = kanjiDatabase.kanjiDao();
       // kanjiRepository = new KanjiRepository(kanjiDao);
       // loadKanjis();


        localKanjiList = MainActivity.kanjiRepository.getKanjisByLevel(levelIndex);
        localDBAdapter.setKanjis(localKanjiList);

        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                levelIndex++;
                //toastsConfig.showToastByDuration(getApplicationContext(), 2, Integer.toString(levelIndex));

                if (levelIndex == 6) {
                    nextLevel.setVisibility(View.INVISIBLE);
                } else {
                    //Making visible the previous level button
                    lastLevel.setVisibility(View.VISIBLE);
                    //Loading the previous kanji dataset

                }
                localKanjiList = MainActivity.kanjiRepository.getKanjisByLevel(levelIndex);
                localDBAdapter.setKanjis(localKanjiList);

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
                }else{
                    //Making visible the next level button
                    nextLevel.setVisibility(View.VISIBLE);
                    //Loading the next kanji dataset
                }

                localKanjiList = MainActivity.kanjiRepository.getKanjisByLevel(levelIndex);
                localDBAdapter.setKanjis(localKanjiList);
                currentLevelTextView.setText("Level " + levelIndex);
            }
        });
    }



}
