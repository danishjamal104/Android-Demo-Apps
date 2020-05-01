package com.example.android.myassistant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<Task> db;

    public TaskAdapter(ArrayList<Task> db) {
        this.db = db;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task currentTask = db.get(position);
        holder.heading.setText(currentTask.getHeading());
        holder.date.setText(currentTask.getDate());
    }

    @Override
    public int getItemCount() {
        return db.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView heading;
        TextView date;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading_listItem_TextView);
            date = itemView.findViewById(R.id.date_listItem_textView);
        }
    }


}
