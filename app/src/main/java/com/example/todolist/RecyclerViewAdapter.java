package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<ToDoTask> mTaskList;
    private Context mContext;

    RecyclerViewAdapter(Context context, ArrayList<ToDoTask> tasks) {
        this.mTaskList = tasks;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(mTaskList.get(position).taskTitle);
        holder.description.setText(mTaskList.get(position).taskDescription);

        final ToDoTask taskData = mTaskList.get(position);

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(mContext, mTaskList.get(position).getTitle() + " removed", Toast.LENGTH_SHORT).show();

                removeTask(taskData);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        LinearLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleView);
            description = itemView.findViewById(R.id.descriptionView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private void removeTask(ToDoTask taskData){

        int currPosition = mTaskList.indexOf(taskData);
        mTaskList.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

}
