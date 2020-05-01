package com.example.danish.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<SpinnerItem> {


    public MyAdapter(Context context, int resourceId, int TextViewId, ArrayList<SpinnerItem> items) {
        super(context, resourceId, TextViewId, items);
    }

    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        SpinnerItem current_item = getItem(position);

        TextView title = (TextView)v.findViewById(R.id.title);
        TextView desc = (TextView)v.findViewById(R.id.description);

        title.setText(current_item.getmTitle());
        desc.setText(current_item.getmDescription());

        return v;

    }*/

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }
        SpinnerItem current_item = getItem(position);

        TextView title = (TextView)convertView.findViewById(R.id.title);
        TextView desc = (TextView)convertView.findViewById(R.id.description);

        title.setText(current_item.getmTitle());
        desc.setText(current_item.getmDescription());

        return convertView;
    }
}
