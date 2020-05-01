package com.example.danish.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by danish on 24/3/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    public String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    public String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    public String formatMagnitude(double magnitude){
        DecimalFormat formatter = new DecimalFormat("0.00");
        return formatter.format(magnitude);
    }



    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        View list_item_view = convertview;
        if(list_item_view == null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake current_earthquake = getItem(position);

        TextView mag = (TextView)list_item_view.findViewById(R.id.magnitude);
        TextView place_offset = (TextView)list_item_view.findViewById(R.id.place_offset);
        TextView place_primary = (TextView)list_item_view.findViewById(R.id.place_primary);
        TextView date = (TextView)list_item_view.findViewById(R.id.date);
        TextView time = (TextView)list_item_view.findViewById(R.id.time);

        Date dateObject = new Date(current_earthquake.getMtimeinmillisecond());

        mag.setText(formatMagnitude(current_earthquake.getMmagnitude()));
        date.setText(formatDate(dateObject));
        time.setText(formatTime(dateObject));

        String place = current_earthquake.getMplace();

        if(place.contains("of")){
            place_primary.setText(place.substring(place.indexOf("f")+2));
            place_offset.setText(place.substring(0, place.indexOf("f")+1));
        }else {
            place_offset.setText("Near the");
            place_primary.setText(place);
        }

        GradientDrawable magnitudeCircle = (GradientDrawable)mag.getBackground();
        int magnitudeColor = getMagnitudeColor(current_earthquake.getMmagnitude());

        magnitudeCircle.setColor(magnitudeColor);


        return list_item_view;
    }

    public int getMagnitudeColor(double mag){
        int magnitudeResourceId;
        int mag_floor = (int)Math.floor(mag);
        switch (mag_floor){
            case 0:
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeResourceId);
    }
}
