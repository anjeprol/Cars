package com.prolan.cars.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.prolan.cars.R;
import com.prolan.cars.model.Make;
import com.prolan.cars.model.Pojo;
import com.prolan.cars.rest.ApiClient;
import com.prolan.cars.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
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
    ImageView imageView;
    private Context context ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = this;
        callService();

    }


    public void callService(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Pojo> call = apiService.getPojo(YEAR,VIEW,FMT,API_KEY);
        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                List<Make> makes = response.body().getMakes();
                loadImg(imageView);
                Log.d(TAG,"Data received:"+makes.size());
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Log.d(TAG,"Error retrieving data: "+t.getMessage());
                Toast.makeText(context,"Unable to retrieve information, loading local data",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
       home();
    }

    public void loadImg(View view) {
        HashMap<String, String> mapMakers = new HashMap<String, String>();
        mapMakers.put("acura", "https://db.tt/VHWzlkhi");
        mapMakers.put("audi", "https://db.tt/EzNGhMQu");
        mapMakers.put("aston martin", "https://db.tt/XNGIDa6q");
        mapMakers.put("nia","https://db.tt/FS5q2zzh");
        imageView = (ImageView) findViewById(R.id.ivTest);
        String brand = "audi";
        if (mapMakers.containsKey(brand)) {

            Picasso.with(this)
                    .load(mapMakers.get(brand))
                    .into(imageView);

        }else{
            Picasso.with(this)
                    .load(mapMakers.get("nia"))
                    .into(imageView);
        }
    }

    public void logout(View view) {
        home();
    }

    private void home(){

        new AlertDialog.Builder(context)
                .setTitle("Logout")
                .setMessage("Are you sure you want to sing out?")
                .setNegativeButton(context.getString(R.string.alert_opt_not), new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        //No actions for now
                    }
                })
                .setPositiveButton(context.getString(R.string.alert_opt_yes), new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .show();


    }
}
