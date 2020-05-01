package com.example.danish.quakereport;

/**
 * Created by danish on 24/3/18.
 */

public class Earthquake {
    private double mmagnitude;
    private String mplace;
    private String murl;
    private long mtimeinmillisecond;

    public Earthquake(double magnitude, String place, long timeinmillisecond, String url){
        mmagnitude = magnitude;
        mplace = place;
        mtimeinmillisecond = timeinmillisecond;
        murl = url;
    }

    public long getMtimeinmillisecond() {
        return mtimeinmillisecond;
    }

    public String getMplace() {
        return mplace;
    }

    public double getMmagnitude() {
        return mmagnitude;
    }

    public String getMurl() {
        return murl;
    }
}
