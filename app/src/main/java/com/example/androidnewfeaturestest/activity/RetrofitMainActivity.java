package com.example.androidnewfeaturestest.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidnewfeaturestest.R;
import com.example.androidnewfeaturestest.retrofittest.MyModel;
import com.example.androidnewfeaturestest.retrofittest.MyRetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created By salih 8.06.2024
 */


public class RetrofitMainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_main_lay);

        mListView = findViewById(R.id.mListView);

        getHeroes();
    }

    private void getHeroes() {
        Call<List<MyModel>> call = MyRetrofitClient.getInstance()
                .getMyApi()
                .getHeroes();


        call.enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                List<MyModel> myModels = response.body();

                assert myModels != null;
                String[] oneHeroes = new String[myModels.size()];
                for (int i = 0; i < myModels.size(); i++) {
                    oneHeroes[i] = myModels.get(i).getName();
                }
                mListView.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        oneHeroes
                ));
            }
            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    // /
}
