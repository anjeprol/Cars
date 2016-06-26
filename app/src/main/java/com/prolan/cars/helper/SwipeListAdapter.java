package com.prolan.cars.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prolan.cars.R;
import com.prolan.cars.activity.DetailsActivity;
import com.prolan.cars.model.Make;
import com.prolan.cars.model.Model;
import com.prolan.cars.model.Pojo;
import com.prolan.cars.model.Year;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by Prolan on 25/06/2016.
 */
public class SwipeListAdapter extends BaseAdapter {
    private Activity activity;
    LayoutInflater inflater;
    private List<Make> pojoList;
    private Context context ;
    HashMap<String, String> mapMakers = new HashMap<String, String>();
    private Intent intent;
    private boolean isEmpty = false;

    public SwipeListAdapter(Activity activity, List<Make> pojoList,Context context) {
        this.activity = activity;
        this.pojoList = pojoList;
        this.context = context;
        this.loadIcons();
        //this.imgView = imgView;
    }

    @Override
    public int getCount() {
        return pojoList.size();
    }

    @Override
    public Object getItem(int positiion) {
        return pojoList.get(positiion);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row,null);

        // Inflating the views
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.ivLogo);
        TextView arr = (TextView) convertView.findViewById(R.id.tvArrow);
        final TextView year = (TextView) convertView.findViewById(R.id.tv_year);


        //Setting the data
        String brand = pojoList.get(position).getName();
        final int modCount = pojoList.get(position).getModels().size();

        if (modCount>0){
            arr.setText(">");
            year.setText("Models ("+modCount+")");
        } else{
            year.setText("Models ("+modCount+")");
            arr.setText(" ");

        }

        title.setText(brand);
        brand = brand.toLowerCase();
        RelativeLayout mRelLay = (RelativeLayout) convertView.findViewById(R.id.rLayout);
        mRelLay.setOnClickListener(new View.OnClickListener(){
        public void onClick(View view){
            List<Model> modelList = pojoList.get(position).getModels();



            Bundle mBundle = new Bundle();
            mBundle.putInt("size",modCount);
            for(int i =0 ; i < modCount ; i++){
                Model model = modelList.get(i);
                mBundle.putString("niceName"+i,model.getNiceName());
                mBundle.putString("name"+i,model.getName());
                mBundle.putInt("year"+i,model.getYears().get(0).getYear());
            }
            intent = new Intent(activity, DetailsActivity.class);
            intent.putExtras(mBundle);
          activity.startActivity(intent);
        }
        });


        if (mapMakers.containsKey(brand.toLowerCase())) {
            Picasso.with(context).load(mapMakers.get(brand)).into(imgView);
            Log.d("DEBUG","Image Done: "+brand+" -- "+mapMakers.get(brand).toLowerCase());
        }else{
            Picasso.with(context).load(mapMakers.get("nia")).into(imgView);
        }



        return convertView;
    }

    private void loadIcons(){
        mapMakers.put("acura", "https://db.tt/VHWzlkhi");
        mapMakers.put("audi", "https://db.tt/EzNGhMQu");
        mapMakers.put("aston martin", "https://db.tt/XNGIDa6q");
        mapMakers.put("mazda", "https://db.tt/SJw6OWMG");
        mapMakers.put("nia","https://db.tt/FS5q2zzh");




    }




}
