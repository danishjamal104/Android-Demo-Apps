package com.example.danish.book_db.data;

import android.net.Uri;
import android.provider.BaseColumns;

public  final class BookContract {

    public BookContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.danish.book_db";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class BookEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, "books");
        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHOR = "author";
    }
}
