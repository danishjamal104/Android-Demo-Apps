package com.example.danish.arev;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danish on 31/3/18.
 */

public class MyLoader extends AsyncTaskLoader<List<Student>> {

    private int noOfStudents;

    public MyLoader(Context context, int noOfStudents) {
        super(context);
        this.noOfStudents = noOfStudents;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Student> loadInBackground() {
        List<Student> students = new ArrayList<>();


        for(int i=0; i<=noOfStudents; i++){
            students.add(new Student("STU__"+i, ""+i));
        }

        return students;
    }
}
