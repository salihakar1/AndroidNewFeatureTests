package com.example.androidnewfeaturestest.roomtest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnewfeaturestest.R;


/**
 * Created By salih 7.06.2024
 */


public class WordViewHolder extends RecyclerView.ViewHolder {

    private final TextView wordItemView;

    private WordViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.itemTextView);
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    static WordViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(view);
    }

// /
}
