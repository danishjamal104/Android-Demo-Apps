package com.example.android.myassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<Task> db = new ArrayList<>();
        db.add(new Task("Heading1", "18 Nov, 2019"));
        db.add(new Task("Heading2", "18 Nov, 2018"));
        db.add(new Task("Heading3", "18 Nov, 2017"));
        db.add(new Task("Heading4", "18 Nov, 2016"));
        db.add(new Task("Heading5", "18 Nov, 2015"));
        db.add(new Task("Heading6", "18 Nov, 2014"));
        db.add(new Task("Heading7", "18 Nov, 2013"));
        db.add(new Task("Heading8", "18 Nov, 2012"));

        TaskAdapter adapter = new TaskAdapter(db);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

    }
}
