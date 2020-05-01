package com.example.danish.book_db;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.danish.book_db.data.BookContract.BookEntry;

import com.example.danish.book_db.data.BookContract;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor c) {
        ((TextView)view.findViewById(R.id.title)).setText(c.getString(c.getColumnIndex(BookEntry.COLUMN_TITLE)));
        ((TextView)view.findViewById(R.id.author)).setText(c.getString(c.getColumnIndex(BookEntry.COLUMN_AUTHOR)));
    }
}
