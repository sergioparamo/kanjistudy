package com.kanjistudy.controllers;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.kanjistudy.R;
import com.kanjistudy.models.Kana;

import java.util.List;

public class KanaAdapter extends RecyclerView.Adapter<KanaAdapter.Holder> {

    public Context context;
    public int layout;
    public static List<Kana> kanaList;
    public String type;

    MediaPlayer mediaPlayer;

    public KanaAdapter(Context context, int layout, List<Kana> kanaList, String type) {
        this.context = context;
        this.layout = layout;
        this.kanaList = kanaList;
        this.type = type;
    }

    @NonNull
    @Override
    public KanaAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kana_item_list, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Kana kana = kanaList.get(position);

        if (type.equals("hiragana")) {
            holder.kanaId.setText(kana.getHiragana());
        } else {
            holder.kanaId.setText(kana.getKatakana());
        }

        holder.buttonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(kana.getPronunciationId(), kana.getRomaji());
            }
        });


    }

    @Override
    public int getItemCount() {
        return kanaList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void setKanas(List<Kana> kanaList) {
        //Setting the list when retrieving the data from the Database
        KanaAdapter.kanaList = kanaList;
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView kanaId;
        MaterialButton buttonSound;

        public Holder(@NonNull View itemView) {
            super(itemView);

            kanaId = itemView.findViewById(R.id.kana_textview_id);
            buttonSound = itemView.findViewById(R.id.kana_sound_id);

        }

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
        toastsConfig.showToastByDuration(context.getApplicationContext(), 1, "Pronunciation of " + kana + "!!");

    }
}
