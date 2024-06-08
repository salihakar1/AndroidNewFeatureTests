package com.example.androidnewfeaturestest.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
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

    private static final String TAG = "RoomMainActivity";
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel mWordViewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_main_activity);

        startActivity(new Intent(this, RetrofitMainActivity.class));

        recyclerView = findViewById(R.id.mRecyclerView);
        WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateAdd(RecyclerView.ViewHolder holder) {
                dispatchAddFinished(holder);
                return true;
            }
        });

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, adapter::submitList);


        mWordViewModel.insert(new Word("test1"));
        mWordViewModel.insert(new Word("test2"));
        mWordViewModel.insert(new Word("test3"));
        mWordViewModel.insert(new Word("test4"));
        mWordViewModel.insert(new Word("test5"));

        FloatingActionButton fab = findViewById(R.id.fab);

        ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RoomMainActivity.RESULT_OK) {
                        Intent data = result.getData();
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

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            Log.e(TAG, "onMove: ");
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAbsoluteAdapterPosition();

            mWordViewModel.deleteWord(((WordListAdapter) recyclerView.getAdapter()).getCurrentList().get(position).getWord());
            recyclerView.getAdapter().notifyItemRemoved(position);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            View itemView = viewHolder.itemView;
            // Draw the red delete background
            ColorDrawable background = new ColorDrawable();
            background.setColor(Color.RED);
            background.setBounds(
                    itemView.getRight() + (int) dX,
                    itemView.getTop(),
                    itemView.getRight(),
                    itemView.getBottom()
            );
            background.draw(c);
            Drawable drawable = ContextCompat.getDrawable(RoomMainActivity.this, R.drawable.delete_button);
            drawable.setBounds(
                    itemView.getLeft(),
                    itemView.getTop(),
                    itemView.getRight(),
                    itemView.getBottom()
            );

            drawable.draw(c);


            // Calculate position of delete icon
//            int iconTop = itemView.getTop() + (itemHeight - inHeight) / 2
//            int iconMargin = (itemHeight - inHeight) / 2
//            int iconLeft = itemView.right - iconMargin - inWidth
//            int iconRight = itemView.right - iconMargin
//            val iconBottom = iconTop + inHeight

            // Draw the delete icon
//            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
//            icon.draw(canvas)

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    // /
}
