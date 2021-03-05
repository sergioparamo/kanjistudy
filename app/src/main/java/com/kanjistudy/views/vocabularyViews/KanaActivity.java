package com.kanjistudy.views.vocabularyViews;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.button.MaterialButton;
import com.kanjistudy.R;
import com.kanjistudy.controllers.KanaAdapter;
import com.kanjistudy.controllers.ToastsConfig;
import com.kanjistudy.database.resources.Data;
import com.kanjistudy.models.Kana;

import java.util.List;

public class KanaActivity extends FragmentActivity {

    public static List<Kana> kanaList;

    //TODO ADD A CONTEXT MENU TO SWITCH BETWEEN LIST AND GRID VIEW
    /*private MenuItem gridButton;
    private MenuItem listButton;*/

    private ListView listView;
    //TODO add grid view
    private GridView gridView;

    public TextView kanaTextView;
    public MaterialButton soundButton;

    @SuppressLint("StaticFieldLeak")
    public static KanaAdapter kanaAdapter;
    public static String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kana_activity);

        //At the moment only list view will be loaded
        listView = findViewById(R.id.kana_listview_id);
        kanaTextView = findViewById(R.id.kana_textview_id);
        soundButton = findViewById(R.id.kana_sound_id);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            type = bundle.getString("type");
            System.out.println("******************************** " + type);

        }

        kanaList = Data.kanaRepository.getAllKanas();

        kanaAdapter = new KanaAdapter(this, R.layout.kana_item_list, kanaList, type);
        listView.setAdapter(kanaAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ToastsConfig toastsConfig = new ToastsConfig();
                toastsConfig.showToastByDuration(getApplicationContext(), 2, "HOLA");


            }
        });


    }

}
