package com.example.todolist;

class ToDoTask {

    String taskTitle;
    String taskDescription;

    ToDoTask(String taskTitle, String taskDescription){
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    String getTitle(){
        return taskTitle;
    }

    String getDescription(){
        return taskDescription;
    }

}
