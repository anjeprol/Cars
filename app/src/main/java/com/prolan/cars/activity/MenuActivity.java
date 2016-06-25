package com.prolan.cars.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.prolan.cars.R;
import com.prolan.cars.model.Make;
import com.prolan.cars.model.Pojo;
import com.prolan.cars.rest.ApiClient;
import com.prolan.cars.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity {
    private final static String API_KEY = "nwzrbm8ptaysjt8au7sev3z9";
    private final static int YEAR = 2014;
    private final static String VIEW = "basic";
    private final static String FMT = "json";
    private static final String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Pojo> call = apiService.getPojo(YEAR,VIEW,FMT,API_KEY);
        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                List<Make> makes = response.body().getMakes();
                Log.d(TAG,"Data recived:"+makes.size());
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {

            }
        });
    }
}
