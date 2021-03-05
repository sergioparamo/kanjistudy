package com.kanjistudy.views.vocabularyViews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.kanjistudy.R;
import com.kanjistudy.controllers.KanaAdapter;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.models.Kana;

import java.util.List;

public class KanaActivity extends FragmentActivity {

    public static List<Kana> kanaList;

    private RecyclerView recyclerView;

    public TextView kanaTextView;
    public MaterialButton soundButton;

    @SuppressLint("StaticFieldLeak")
    public static KanaAdapter kanaAdapter;
    public static String type;

    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_kana_activity);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerkana_layout);
        toolbar = findViewById(R.id.topAppBarKana);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );


        recyclerView = findViewById(R.id.recyclerKana);
        kanaTextView = findViewById(R.id.kana_textview_id);
        soundButton = findViewById(R.id.kana_sound_id);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            type = bundle.getString("type");
            System.out.println("******************************** " + type);

        }

        kanaList = Data.kanaRepository.getAllKanas();

        kanaAdapter = new KanaAdapter(this, R.layout.kana_item_list, kanaList, type);


        recyclerView.setAdapter(kanaAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

}
