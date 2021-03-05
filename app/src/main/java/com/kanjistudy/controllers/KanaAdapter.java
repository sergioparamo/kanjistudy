package com.kanjistudy.controllers;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.google.android.material.button.MaterialButton;
import com.kanjistudy.R;
import com.kanjistudy.models.Kana;

import java.util.List;

public class KanaAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private static List<Kana> kanaList;
    private String type;

    MediaPlayer mediaPlayer;

    public KanaAdapter(Context context, int layout, List<Kana> kanaList, String type) {
        this.context = context;
        this.layout = layout;
        this.kanaList = kanaList;
        this.type = type;
    }


    @Override
    public int getCount() {
        return this.kanaList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.kanaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.layout, null);

            viewHolder = new ViewHolder();
            viewHolder.kanaTextView = convertView.findViewById(com.kanjistudy.R.id.kana_textview_id);
            viewHolder.kanaSound = convertView.findViewById(R.id.kana_sound_id);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Kana currentKana = (Kana) getItem(position);

        if (type.equals("hiragana")) {

            viewHolder.kanaTextView.setText(currentKana.getHiragana());
        } else {
            viewHolder.kanaTextView.setText(currentKana.getKatakana());
        }

        viewHolder.kanaSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(currentKana.getPronunciationId(),currentKana.getRomaji());
            }
        });





        return convertView;
    }

    static class ViewHolder {
        private TextView kanaTextView;
        private MaterialButton kanaSound;
    }


    public void setKanas(List<Kana> kanaList) {
        //Setting the list when retrieving the data from the Database
        KanaAdapter.kanaList = kanaList;
        notifyDataSetChanged();
    }

    private void playSound(int soundId, String kana) {

        ToastsConfig toastsConfig = new ToastsConfig();

        //Si un sonido ya se esta ejecutando lo paramos
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), soundId);
        } else {
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), soundId);
        }

        mediaPlayer.start();
        toastsConfig.showToastByDuration(context.getApplicationContext(), 2, "Pronunciation of " + kana + "!!");

    }
}
