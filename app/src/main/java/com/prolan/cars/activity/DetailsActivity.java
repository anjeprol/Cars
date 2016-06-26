package com.prolan.cars.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.prolan.cars.R;
import com.prolan.cars.model.Model;
import com.prolan.cars.model.Year;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        Bundle mBundle = intent.getExtras();
        int size = mBundle.getInt("size");
        List<Model> modelList = new ArrayList<Model>();
        Model model ;
        List<Year> yearList = new ArrayList<Year>();
        Year mYear ;

        for(int i = 0 ; i < size ; i++){
            mYear = new Year() ;
            model = new Model();
            model.setNiceName(mBundle.getString("niceName"+i));
            model.setName(mBundle.getString("name"+i));
            mYear.setYear(mBundle.getInt("year"+i));
            yearList.add(mYear);
            modelList.add(model);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
