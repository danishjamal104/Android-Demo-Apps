package com.example.danish.arev;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Student>> {

    RecyclerView list;

    StudentAdapter mAdapter;

    LoaderManager loaderManager;

    int no = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderManager = getLoaderManager();

        list = (RecyclerView)findViewById(R.id.recycler_view);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));


        mAdapter = new StudentAdapter(this, new ArrayList<Student>());

        list.setAdapter(mAdapter);

        loaderManager.initLoader(0, null, this);

    }


    @Override
    public Loader<List<Student>> onCreateLoader(int i, Bundle bundle) {
        return new MyLoader(this, no);
    }

    @Override
    public void onLoadFinished(Loader<List<Student>> loader, List<Student> students) {
        mAdapter.clear();

        if(students != null && !students.isEmpty()){
            mAdapter.addAll(students);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Student>> loader) {
        mAdapter.clear();
    }
}
