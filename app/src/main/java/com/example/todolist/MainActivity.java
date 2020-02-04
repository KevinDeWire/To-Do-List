package com.example.todolist;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<ToDoTask> mTaskList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*        if(savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            mTaskList = new ArrayList<ToDoTask>();
        }
        else {
            mTaskList = savedInstanceState.getParcelableArrayList("key");
        }*/

        if (mTaskList.isEmpty()){
            loadTaskList();
        }

        setContentView(R.layout.activity_main);

        buildRecyclerView();

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(v);
                saveTaskListToText();
            }
        });
    }

/*    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", mTaskList);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        mTaskList = savedInstanceState.getParcelableArrayList("key");
    }*/

    private void buildRecyclerView(){
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

        adapter.notifyDataSetChanged();

        title.setText("");
        description.setText("");
    }

    // The task list is converted into a String and then saved to a file in the Download file.
    public static void saveTaskListToText() {

        BufferedWriter writer;
        StringBuilder taskListOutput = new StringBuilder();
        File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "toDoList.txt");

        for (int i = 0; i < mTaskList.size(); i++){
            taskListOutput.append(mTaskList.get(i).getTitle());
            taskListOutput.append("\n");
            taskListOutput.append(mTaskList.get(i).getDescription());
            taskListOutput.append("\n");
        }

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

    //Checks for a save file, if there is one it is loaded into the task list.
    public static void loadTaskList (){

        File inputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "toDoList.txt");
        Scanner scanner;
        String title;
        String desciption;
        ToDoTask newTask;

        if (inputFile.exists()){
            try {
                scanner = new Scanner( new FileReader( inputFile.getAbsolutePath()));
                while (scanner.hasNext()) {
                    title = scanner.nextLine();
                    desciption = scanner.nextLine();
                    newTask = new ToDoTask(title, desciption);
                    mTaskList.add(newTask);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
