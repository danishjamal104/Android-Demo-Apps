package com.example.danish.book_db.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.danish.book_db.data.BookContract.BookEntry;

import java.util.IllformedLocaleException;

public class BookProvider extends ContentProvider {
    private static final int BOOK = 400;
    private static final int BOOK_ID = 4002;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY, "books", BOOK);
        sUriMatcher.addURI(BookContract.CONTENT_AUTHORITY, "books/#", BOOK_ID);
    }
    private BookDbHelper mDB;
    @Override
    public boolean onCreate() {
        mDB = new BookDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase DB = mDB.getReadableDatabase();
        Cursor c;
        int match = sUriMatcher.match(uri);
        switch (match){
            case BOOK:
                c = DB.query(BookEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;
            case BOOK_ID:
                selection = BookEntry._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                c = DB.query(BookEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new IllegalArgumentException("Error");
        }

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match){
            case BOOK:
                SQLiteDatabase DB = mDB.getWritableDatabase();
                long id = DB.insert(BookEntry.TABLE_NAME, null, values);
                if(id == -1){
                    Log.e("Eroor", "Error");
                }

                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            default:
                throw new IllegalArgumentException("Eroor");
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase DB = mDB.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        switch (match){
            case BOOK_ID:
                selection = BookEntry._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int rowsAffected = DB.delete(BookEntry.TABLE_NAME, selection, selectionArgs);
                if(rowsAffected != 0){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsAffected;

            case BOOK:
                String SQL_STATEMENT = "DELETE FROM " + BookEntry.TABLE_NAME + " WHERE " + BookEntry._ID +
                        " LIKE \"%\" ";
                DB.execSQL(SQL_STATEMENT);
                getContext().getContentResolver().notifyChange(uri, null);
                return 1;
            default:
                throw new IllegalArgumentException("Error");
        }

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int match = sUriMatcher.match(uri);
        switch (match){
            case BOOK:
                return updateBook(uri, values, selection, selectionArgs);
            case BOOK_ID:
                selection = BookEntry._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateBook(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Error");
        }
    }

    private int updateBook(Uri uri, ContentValues v, String selection, String[] selectionArgs){
        if(v.size() == 0){
            return 0;
        }
        SQLiteDatabase DB = mDB.getWritableDatabase();
        int rows_Affected = DB.update(BookEntry.TABLE_NAME, v, selection, selectionArgs);
        if(rows_Affected != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rows_Affected;
    }
}
