package com.prolan.cars.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.prolan.cars.R;
import com.prolan.cars.helper.SwipeListAdapter;
import com.prolan.cars.model.Make;
import com.prolan.cars.model.Pojo;
import com.prolan.cars.rest.ApiClient;
import com.prolan.cars.rest.ApiInterface;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private final static String API_KEY = "nwzrbm8ptaysjt8au7sev3z9";
    private final static int YEAR = 2014;
    private final static String VIEW = "basic";
    private final static String FMT = "json";
    private static final String TAG = "DEBUG";
    private Context context ;


    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListAdapter adapter;
    private Call<Pojo> call ;
    private List<Make> makes;
   // private List<Pojo> pojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        context = this;

        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);


        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                callService();
            }
        });

    }


    public void callService(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        call = apiService.getPojo(YEAR,VIEW,FMT,API_KEY);
        call.enqueue(new Callback<Pojo>() {
            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {
                makes = response.body().getMakes();
               // loadImg(imageView);
                Log.d(TAG,"Data received:"+makes.size());


                adapter = new SwipeListAdapter((Activity) context,makes,context);
                listView.setAdapter(adapter);
                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Log.d(TAG,"Error retrieving data: "+t.getMessage());
                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(context,"Unable to retrieve information, loading local data",Toast.LENGTH_LONG).show();
            }
        });
    }



    @Override
    public void onBackPressed() {
       home();
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

    @Override
    public void onRefresh() {
        callService();
    }
}
