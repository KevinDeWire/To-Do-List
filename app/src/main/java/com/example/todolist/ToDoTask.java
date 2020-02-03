package com.example.todolist;

import java.io.Serializable;

public class ToDoTask implements Serializable {

    String taskTitle;
    String taskDescription;

    public ToDoTask(String taskTitle, String taskDescription){
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    public String getTaskTitle(){
        return taskTitle;
    }

    public String getTaskDescription(){
        return taskDescription;
    }

}
