package com.kanjistudy.views.vocabularyViews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.google.android.material.button.MaterialButton;
import com.kanjistudy.R;
import com.kanjistudy.controllers.KanaAdapter;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.models.Kana;
import com.kanjistudy.views.KanjiMenuActivity;
import com.kanjistudy.views.LandingActivity;

import org.w3c.dom.Text;

import java.util.List;

public class KanaActivity extends FragmentActivity {

    public static List<Kana> kanaList;

    private RecyclerView recyclerView;

    public TextView kanaTextView;
    public TextView kanaTypeDescriptionTextView;
    public MaterialButton soundButton;

    @SuppressLint("StaticFieldLeak")
    public static KanaAdapter kanaAdapter;
    public static String type;

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;
    ActionBarDrawerToggle toggle;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_kana_activity);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_kana);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_bar_home:
                        Intent fromKanaToLandingIntentBottom = new Intent(KanaActivity.this, LandingActivity.class);
                        startActivity(fromKanaToLandingIntentBottom);
                        break;
                    case R.id.bottom_bar_kanji:
                        Intent fromKanaToKanjiIntentBottom = new Intent(KanaActivity.this, KanjiMenuActivity.class);
                        startActivity(fromKanaToKanjiIntentBottom);
                        break;
                    case R.id.bottom_bar_hiragana:
                        Intent fromKanaToHiraganaIntentBottom = new Intent(KanaActivity.this, KanaActivity.class);
                        fromKanaToHiraganaIntentBottom.putExtra("type", "hiragana");
                        startActivity(fromKanaToHiraganaIntentBottom);
                        break;
                    case R.id.bottom_bar_katakana:
                        Intent fromKanaToKatakanaIntentBottom = new Intent(KanaActivity.this, KanaActivity.class);
                        fromKanaToKatakanaIntentBottom.putExtra("type", "katakana");
                        startActivity(fromKanaToKatakanaIntentBottom);
                        break;
                }

                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerkana_layout);
        toolbar = findViewById(R.id.topAppBarKana);

        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        recyclerView = findViewById(R.id.recyclerKana);
        kanaTextView = findViewById(R.id.kana_textview_id);
        kanaTypeDescriptionTextView = findViewById(R.id.kanaTitleDescriptionTypeId);
        soundButton = findViewById(R.id.kana_sound_id);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            type = bundle.getString("type");
            System.out.println("******************************** " + type);

        }

        if (type.equals("hiragana")){
            kanaTypeDescriptionTextView.setText("Hiragana Dictionary");
        }else {
            kanaTypeDescriptionTextView.setText("Katakana Dictionary");
        }



        kanaList = Data.kanaRepository.getAllKanas();

        kanaAdapter = new KanaAdapter(this, R.layout.kana_item_list, kanaList, type);


        recyclerView.setAdapter(kanaAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

}
