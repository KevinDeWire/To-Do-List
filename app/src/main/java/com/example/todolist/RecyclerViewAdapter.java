package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        holder.deleteBox.setChecked(false);

        final ToDoTask taskData = mTaskList.get(position);
        CheckBox deleteBox = holder.itemView.findViewById(R.id.checkBox);

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeTask(taskData);
                return true;
            }
        });

        deleteBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTask(taskData);
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
        CheckBox deleteBox;
        LinearLayout parentLayout;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleView);
            description = itemView.findViewById(R.id.descriptionView);
            deleteBox = itemView.findViewById(R.id.checkBox);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }

    private void removeTask(ToDoTask taskData){

        int currPosition = mTaskList.indexOf(taskData);
        Toast.makeText(mContext, mTaskList.get(currPosition).getTitle() + " removed", Toast.LENGTH_SHORT).show();
        mTaskList.remove(currPosition);
        notifyItemRemoved(currPosition);
        MainActivity.saveTaskListToText();

    }

}
