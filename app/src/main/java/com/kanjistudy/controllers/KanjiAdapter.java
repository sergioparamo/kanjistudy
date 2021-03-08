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
import com.kanjistudy.models.Kanji;

import java.util.List;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.Holder> {

    static List<Kanji> kanjiList;
    MediaPlayer mediaPlayer;
    Context context;

    public KanjiAdapter(Context context, List<Kanji> kanjiList) {
        KanjiAdapter.kanjiList = kanjiList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kanji_list, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Kanji kanji = kanjiList.get(position);
        holder.textViewNameValue.setText(kanji.getKanji());
        holder.meaning.setText(kanji.getMeaning());
        holder.romaji.setText(kanji.getRomaji());

        holder.buttonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(kanji.getSoundId(), kanji.getKanji());
            }
        });


    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }

    public void setKanjis(List<Kanji> kanjiList) {

        //Setting the list when retrieving the data from the API
        KanjiAdapter.kanjiList = kanjiList;
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView textViewNameValue;
        TextView meaning;
        TextView romaji;
        MaterialButton buttonSound;


        public Holder(@NonNull View itemView) {
            super(itemView);

            textViewNameValue = itemView.findViewById(R.id.characterValueTextView);
            romaji = itemView.findViewById(R.id.kanji_romaji_id);
            meaning = itemView.findViewById(R.id.kanji_meaning_id);
            buttonSound = itemView.findViewById(R.id.kanji_button_sound_id);

        }
    }

    private void playSound(int soundId, String kanji) {

        ToastsConfig toastsConfig = new ToastsConfig();

        //Si un sonido ya se esta ejecutando lo paramos
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), soundId);
        } else {
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), soundId);
        }

        mediaPlayer.start();
        toastsConfig.showToastByDuration(context.getApplicationContext(), 1, "Pronunciation of " + kanji + "!!");

    }
}
