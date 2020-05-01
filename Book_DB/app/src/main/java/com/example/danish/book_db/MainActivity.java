package com.example.danish.book_db;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.danish.book_db.data.BookContract.BookEntry;

import com.example.danish.book_db.data.BookContract;
import com.example.danish.book_db.data.BookDbHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.apache.poi.hpsf.Util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BOFRecord;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import static jxl.format.PaperSize.C;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private boolean isActive = false;

    private FloatingActionButton fab_main=null;
    private FloatingActionButton fab_del=null;
    private FloatingActionButton fab_dummy=null;
    private FloatingActionButton fab_add=null;

    private ListView listView;
    private BookCursorAdapter mAdapter;

    private AdView mAdView;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-9017042451125677~3680573684");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        __init__button__click__event();
        listView = (ListView) findViewById(R.id.list_view);
        mAdapter = new BookCursorAdapter(this, null);

        listView.setAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                Uri uri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, id);
                intent.setData(uri);
                startActivity(intent);
            }
        });

    }
    private void animate(){
        if(!isActive){
            fab_main.animate().rotation(50);
            fab_dummy.animate().translationX(-250);
            fab_dummy.animate().translationY(50);
            fab_del.animate().translationY(-250);
            fab_del.animate().translationX(50);
            fab_add.animate().translationX(-175);
            fab_add.animate().translationY(-175);
            isActive=true;
        }else if(isActive){
            fab_main.animate().rotation(0);
            fab_dummy.animate().translationX(0);
            fab_dummy.animate().translationY(0);
            fab_del.animate().translationY(0);
            fab_del.animate().translationX(0);
            fab_add.animate().translationX(0);
            fab_add.animate().translationY(0);
            isActive=false;
        }
    }
    private void __init__button__click__event(){
        fab_main = (FloatingActionButton) findViewById(R.id.fab_new);
        fab_del = (FloatingActionButton)findViewById(R.id.fab_delete);
        fab_dummy = (FloatingActionButton)findViewById(R.id.fab_dummy);
        fab_add = (FloatingActionButton)findViewById(R.id.fab_add);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate();
            }
        });
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });
        fab_dummy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(BookEntry.COLUMN_TITLE, "Dummy_Data");
                values.put(BookEntry.COLUMN_AUTHOR, "Dummy_Data");
                Uri result = getContentResolver().insert(BookEntry.CONTENT_URI, values);
            }
        });
        fab_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete = getContentResolver().delete(BookEntry.CONTENT_URI, null, null);
                Toast.makeText(MainActivity.this, "ALL BOOKS DELETED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {BookEntry._ID, BookEntry.COLUMN_TITLE, BookEntry.COLUMN_AUTHOR};
        return new CursorLoader(this, BookEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.csv){
            generate_excel();
        }
        return super.onOptionsItemSelected(item);
    }

    private void generate_excel() {
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        SQLiteToExcel a = new SQLiteToExcel(this, "books.db", directory_path);

        a.exportSingleTable(BookEntry.TABLE_NAME, "books.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {
            }
            @Override
            public void onCompleted(String filePath) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(Exception e) {
            }
        });
    }
}
