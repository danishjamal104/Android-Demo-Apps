package com.example.android.myassistant;

public class Task {

    String heading;
    String date;

    public Task(String heading, String date) {
        this.heading = heading;
        this.date = date;
    }

    public Task() {
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
