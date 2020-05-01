package com.example.danish.collapsingtoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String s = "34";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RecyclerView listView = (RecyclerView)findViewById(R.id.list);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");
        items.add("F");
        items.add("G");
        items.add("H");
        items.add("I");
        items.add("J");
        items.add("H");
        items.add("J");
        items.add("K");
        items.add("L");
        items.add("M");
        items.add("N");
        items.add("O");
        items.add("P");
        items.add("Q");
        items.add("R");
        items.add("S");



        myAdapter adapter = new myAdapter(this, items);

        listView.setAdapter(adapter);
    }
}
