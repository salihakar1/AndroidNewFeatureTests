package com.example.androidnewfeaturestest.roomtest;

import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * Created By salih 7.06.2024
 */


public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord());
        Log.e("TAG", "onBindViewHolder: " + position);
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }

    }


    // /
}
