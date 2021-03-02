package com.kanjistudy.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.kanjistudy.R;
import com.kanjistudy.controllers.KanaAdapter;
import com.kanjistudy.models.Kana;

import java.util.List;

public class KanaActivity extends FragmentActivity {

    public static List<Kana> kanaList;

    //TODO ADD A CONTEXT MENU TO SWITCH BETWEEN LIST AND GRID VIEW
    /*private MenuItem gridButton;
    private MenuItem listButton;*/

    private ListView listView;
    private GridView gridView;

    public TextView hiraganaTextView;
    public TextView katakanaTextView;

    @SuppressLint("StaticFieldLeak")
    public static KanaAdapter kanaAdapter;
    public static String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kana_activity);

        //At the moment only list view will be loaded
        listView = findViewById(R.id.kana_listview_id);
        hiraganaTextView = findViewById(R.id.hiragana_id);
        katakanaTextView = findViewById(R.id.katakana_id);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            type = bundle.getString("type");
            System.out.println("******************************** " + type);
            
        }

        kanaList = MainActivity.kanaRepo.getAllKanas();

        kanaAdapter = new KanaAdapter(this, R.layout.kana_item_list, kanaList, type);
        listView.setAdapter(kanaAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


    }
}
