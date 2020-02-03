package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDoTask> mTaskList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    private final String toDoListSave = "toDoList.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this, mTaskList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        recyclerView.addItemDecoration(itemDecor);

    }

    public void addTask(View view) {

        EditText title = findViewById(R.id.titleText);
        EditText description = findViewById(R.id.descriptionText);

        String taskTitle = title.getText().toString();
        String taskDescription = description.getText().toString();

        ToDoTask newTask = new ToDoTask(taskTitle, taskDescription);

        mTaskList.add(newTask);

        saveTaskList();

        adapter.notifyDataSetChanged();

        title.setText("");
        description.setText("");
    }

    private void saveTaskList() {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                // create a file in downloads directory
                FileOutputStream fos =
                        new FileOutputStream(
                                new File(Environment.getExternalStoragePublicDirectory(
                                        Environment.DIRECTORY_DOWNLOADS), "toDoList.txt")
                        );
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(mTaskList);
                os.close();

            } catch(Exception ex) {
                ex.printStackTrace();

            }

        }

    }

}
