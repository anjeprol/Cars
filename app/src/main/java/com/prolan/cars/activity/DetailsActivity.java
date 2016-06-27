package com.prolan.cars.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.prolan.cars.R;
import com.prolan.cars.helper.ListAdapterDetails;
import com.prolan.cars.model.Model;
import com.prolan.cars.model.Year;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private Intent intent ;
    ListAdapterDetails adapterDetails;
    ListView listViewDetail;
    List<Model> modelList = new ArrayList<Model>();
    List<Year> yearList = new ArrayList<Year>();
    Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        listViewDetail = (ListView) findViewById(R.id.listViewDetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        intent = getIntent();
        modelList = getExtras(modelList);
        toolbar.setTitle(mBundle.getString("maker"));
        setSupportActionBar(toolbar);
        infListView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private List<Model> getExtras(List<Model> modelListExtra){

        mBundle = intent.getExtras();
        int size = mBundle.getInt("size");
        Model model ;
        Year mYear ;

        for(int i = 0 ; i < size ; i++){
            mYear = new Year() ;
            model = new Model();
            model.setId(mBundle.getString("id"+i));
            model.setNiceName(mBundle.getString("niceName"+i));
            mYear.setYear(mBundle.getInt("year"+i));
            yearList.add(mYear);
            model.setYears(yearList);
            modelListExtra.add(model);
        }

        return modelListExtra;
    }

    private void infListView(){
        adapterDetails = new ListAdapterDetails(this,modelList);
        listViewDetail.setAdapter(adapterDetails);
    }

}
