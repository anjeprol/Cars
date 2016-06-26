package com.prolan.cars.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.prolan.cars.R;
import com.prolan.cars.model.Make;
import com.prolan.cars.model.Pojo;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Prolan on 25/06/2016.
 */
public class SwipeListAdapter extends BaseAdapter {
    private Activity activity;
    LayoutInflater inflater;
    private List<Make> pojoList;
    private Context context ;

    public SwipeListAdapter(Activity activity, List<Make> pojoList,Context context) {
        this.activity = activity;
        this.pojoList = pojoList;
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row,null);

        // Inflating the views
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.ivLogo);
        //   TextView year = (TextView) convertView.findViewById(R.id.tv_year);


        //Setting the data
        String brand = pojoList.get(position).getName();
        title.setText(brand);

        brand = brand.toLowerCase();



        HashMap<String, String> mapMakers = new HashMap<String, String>();
        mapMakers.put("acura", "https://db.tt/VHWzlkhi");
        mapMakers.put("audi", "https://db.tt/EzNGhMQu");
        mapMakers.put("aston martin", "https://db.tt/XNGIDa6q");
        mapMakers.put("nia","https://db.tt/FS5q2zzh");
        //Picasso.with(context).load(mapMakers.get(brand)).into(imgView);
        //Picasso.with(context).load("https://db.tt/VHWzlkhi").into(imgView);
        if (mapMakers.containsKey(brand.toLowerCase())) {

            Picasso.with(context).load(mapMakers.get(brand)).into(imgView);
            Log.d("DEBUG","Image Done: -- "+mapMakers.get(brand).toLowerCase());
        }
  //      year.setText();


        return convertView;
    }




}
