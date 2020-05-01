package com.example.danish.booksearch;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Book>> {

    private static final int BOOK_LOADER_ID = 0;
    public static String url = "https://www.googleapis.com/books/v1/volumes?q=ios";

    private MyAdapter mAdapter;
    private RecyclerView list;
    private LoaderManager loaderManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderManager = getLoaderManager();

        list = (RecyclerView)findViewById(R.id.list_view);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new MyAdapter(this, new ArrayList<Book>());
        list.setAdapter(mAdapter);



        loaderManager.initLoader(BOOK_LOADER_ID, null, this);

    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new MyLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        mAdapter.clear();
        if(books != null && !books.isEmpty()){
            mAdapter.addAll(books);
            mAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        mAdapter.clear();
    }
}
