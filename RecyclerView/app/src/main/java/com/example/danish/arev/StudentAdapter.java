package com.example.danish.arev;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danish on 31/3/18.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<Student> mstudents;
    private Context mcontext;

    public StudentAdapter(Context context, ArrayList<Student> students) {
        mstudents = students;
        mcontext = context;
    }

    public void clear(){
        mstudents.clear();
    }

    public void addAll(List<Student> students){
        mstudents.addAll(students);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student currentStudent = mstudents.get(position);
        holder.name.setText(currentStudent.getmName());
        holder.age.setText(currentStudent.getmAge());
    }

    @Override
    public int getItemCount() {
        return mstudents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView age;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name_text_view);
            age = (TextView)itemView.findViewById(R.id.age_text_view);
        }
    }
}
