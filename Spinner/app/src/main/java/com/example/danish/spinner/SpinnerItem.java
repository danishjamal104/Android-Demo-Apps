package com.example.danish.spinner;

public class SpinnerItem {

    private String mTitle;
    private String mDescription;

    public SpinnerItem(String title, String description) {
        this.mTitle = title;
        this.mDescription = description;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }
}
