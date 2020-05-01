package com.example.danish.book_db;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.danish.book_db.data.BookContract.BookEntry;

public class EditorActivity extends AppCompatActivity {

    private static int mode;
    private Uri mCurrentPetUri;

    private EditText title;
    private EditText author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();
        title = (EditText)findViewById(R.id.title);
        author = (EditText)findViewById(R.id.author);
        setMode();
    }

    private  void setMode(){
        if(mCurrentPetUri != null){
            mode = 2;
            setTitle("Edit Book");
            String[] projection = {BookEntry._ID, BookEntry.COLUMN_TITLE, BookEntry.COLUMN_AUTHOR};
            Cursor c = getContentResolver().query(mCurrentPetUri, projection, null, null, null);
            while(c.moveToNext()){
                title.setText(c.getString(c.getColumnIndex(BookEntry.COLUMN_TITLE)));
                author.setText(c.getString(c.getColumnIndex(BookEntry.COLUMN_AUTHOR)));
            }
        }else {
            mode = 1;
            setTitle("New Book");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mode == 1){
            getMenuInflater().inflate(R.menu.menu_mode_1, menu);
        }else {
            getMenuInflater().inflate(R.menu.menu_mode_2, menu);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_TITLE, title.getText().toString().trim());
        values.put(BookEntry.COLUMN_AUTHOR, author.getText().toString().trim());

        if(mode == 1 && item.getItemId() == R.id.save){
            Uri result = getContentResolver().insert(BookEntry.CONTENT_URI, values);
        }else{
            if(item.getItemId() == R.id.delete) {
                int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);
                if (rowsDeleted == 0) {
                    toast("Delete failed");
                } else {
                    toast("Book deleted successfully");
                }

            }else {
                int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);
                if(rowsAffected == 0){
                    toast("Update failed");
                }else {
                    toast("Updated successfully");
                }
            }


        }
        startActivity(new Intent(EditorActivity.this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private  void toast(String text){
        Toast.makeText(EditorActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}
