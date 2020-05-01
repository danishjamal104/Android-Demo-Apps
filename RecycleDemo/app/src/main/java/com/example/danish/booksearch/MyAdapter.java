package com.example.danish.booksearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danish on 31/3/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Book> mBooks;
    private Context mContext;

    public MyAdapter(Context Context, ArrayList<Book> Books) {
        mBooks = Books;
        mContext = Context;
    }

    public void addAll(List<Book> books){
        mBooks.addAll(books);
    }

    public void clear(){
        mBooks.clear();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book currentBook = mBooks.get(position);

        holder.title.setText(currentBook.getmTitle());
        holder.author.setText(currentBook.getmAuthor());

        Picasso.get().load(currentBook.getmImageLink()).fit().into(holder.bookThumbnail);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView author;
        ImageView bookThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            author = (TextView)itemView.findViewById(R.id.author);
            bookThumbnail = (ImageView)itemView.findViewById(R.id.book_thumbnail);
        }
    }
}
