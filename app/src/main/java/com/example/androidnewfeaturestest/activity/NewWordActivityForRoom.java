package com.example.androidnewfeaturestest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidnewfeaturestest.R;
import com.example.androidnewfeaturestest.roomtest.WordViewModel;

/**
 * Created By salih 7.06.2024
 */


public class NewWordActivityForRoom extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.salih.REPLY";
    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word_for_room);

        editText = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(v -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editText.getText().toString())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = editText.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
    // /
}
