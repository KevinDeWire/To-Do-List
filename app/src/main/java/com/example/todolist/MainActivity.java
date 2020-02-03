package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

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

    // the task title and descriptionare read from the edit test blocks and added to the ArrayList holding all tasks
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

    // The task list is converted into a String and then saved to a file in the Download file.
    private void saveTaskList() {

        BufferedWriter writer;
        StringBuilder taskListOutput = new StringBuilder();

        for (int i = 0; i < mTaskList.size(); i++){
            taskListOutput.append(mTaskList.get(i).getTaskTitle());
            taskListOutput.append(" : ");
            taskListOutput.append(mTaskList.get(i).getTaskDescription());
            taskListOutput.append("\n");
        }

        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "toDoList.txt");

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {

                writer = new BufferedWriter( new FileWriter( outputFile.getAbsolutePath(), false ));
                writer.write(taskListOutput.toString());
                writer.close();

            } catch(Exception ex) {
                ex.printStackTrace();

            }

        }

    }

}
