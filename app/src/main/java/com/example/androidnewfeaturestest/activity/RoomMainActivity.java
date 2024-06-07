package com.example.androidnewfeaturestest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnewfeaturestest.R;
import com.example.androidnewfeaturestest.roomtest.Word;
import com.example.androidnewfeaturestest.roomtest.WordListAdapter;
import com.example.androidnewfeaturestest.roomtest.WordViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Created By salih 7.06.2024
 */

public class RoomMainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel mWordViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_main_activity);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);


        ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                o -> {
                    if (o.getResultCode() == RoomMainActivity.RESULT_OK) {
                        Intent data = o.getData();
                        assert data != null;
                        String word = data.getStringExtra(NewWordActivityForRoom.EXTRA_REPLY);
                        assert word != null;
                        Word word1 = new Word(word);
                        mWordViewModel.insert(word1);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Word empty",
                                Toast.LENGTH_LONG).show();
                    }
                });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewWordActivityForRoom.class);
            myLauncher.launch(intent);
        });

    }

    // /
}
