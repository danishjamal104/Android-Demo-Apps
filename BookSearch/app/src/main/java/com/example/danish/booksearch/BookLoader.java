package com.example.danish.booksearch;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by danish on 28/3/18.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    private String mUrl;

    public BookLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if(mUrl == null){
            return null;
        }
        List<Book> books = Utils.fetchBookData(mUrl);
        return books;
    }
}
