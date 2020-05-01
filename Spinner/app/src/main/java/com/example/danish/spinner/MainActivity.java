package com.example.danish.spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String[] lang = {"JAVA", "SWIFT", "PYTHON", "JS", "HTML"};
    String[] ide = {"AndroidStudio", "Xcode", "IDE", "VisualStudio", "SublimeText"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayList<SpinnerItem> list= new ArrayList<>();

        for(int i=0; i<lang.length; i++){
            list.add(new SpinnerItem(lang[i], ide[i]));
        }

        MyAdapter adapter = new MyAdapter(this, R.layout.list_item, R.id.title, list);

        spinner.setAdapter(adapter);







    }
}
