package com.prolan.cars.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prolan.cars.R;
import com.prolan.cars.model.Model;

import java.util.List;

/**
 * Created by Prolan on 26/06/2016.
 */
public class ListAdapterDetails extends BaseAdapter{

    private Activity activity;
    private List<Model> modelList;
    LayoutInflater inflater;

    public ListAdapterDetails(Activity activity, List<Model> modelList) {
        this.activity = activity;
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.list_row_details,null);

        //Inflating the view

        TextView name = (TextView) convertView.findViewById(R.id.tvName);
        TextView niceName = (TextView) convertView.findViewById(R.id.tvNiceName);
        TextView year = (TextView) convertView.findViewById(R.id.tv_yearModel);

        name.setText(modelList.get(position).getNiceName());
        niceName.setText(modelList.get(position).getId());
        year.setText(modelList.get(position).getYears().get(position).getYear().toString());

        return convertView;
    }
}
