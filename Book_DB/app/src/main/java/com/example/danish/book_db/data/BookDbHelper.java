package com.example.danish.book_db.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.danish.book_db.data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "books.db";
    private static final int DB_VERSION = 1;

    public BookDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE books(_ID INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT);
        final String SQL_TABLE_CREATE = "CREATE TABLE " + BookEntry.TABLE_NAME +
                "(" + BookEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BookEntry.COLUMN_TITLE + " TEXT, " +
                BookEntry.COLUMN_AUTHOR + " TEXT);";

        db.execSQL(SQL_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
