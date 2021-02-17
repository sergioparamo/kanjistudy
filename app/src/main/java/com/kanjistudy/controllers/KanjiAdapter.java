package com.kanjistudy.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kanjistudy.R;
import com.kanjistudy.models.KanjiDb;

import java.util.List;

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.Holder> {

    static List<KanjiDb> kanjiList;

    public KanjiAdapter(List<KanjiDb> kanjiList) {
        KanjiAdapter.kanjiList = kanjiList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.kanji_list, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        KanjiDb kanji = kanjiList.get(position);
        holder.textViewNameValue.setText(kanji.getKanji());
    }

    @Override
    public int getItemCount() {
        return kanjiList.size();
    }

    public void setKanjis(List<KanjiDb> kanjiList) {

        //Setting the list when retrieving the data from the API
        KanjiAdapter.kanjiList = kanjiList;
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        TextView textViewNameValue;

        public Holder(@NonNull View itemView) {
            super(itemView);

            textViewNameValue = itemView.findViewById(R.id.characterValueTextView);

        }
    }
}
