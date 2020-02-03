package com.example.todolist;

import android.os.Parcel;
import android.os.Parcelable;

class ToDoTask implements Parcelable {

    String taskTitle;
    String taskDescription;

    ToDoTask(String taskTitle, String taskDescription){
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    private ToDoTask(Parcel in) {
        taskTitle = in.readString();
        taskDescription = in.readString();
    }
    public int describeContents() {
        return 0;
    }

    String getTitle(){
        return taskTitle;
    }

    String getDescription(){
        return taskDescription;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(taskTitle);
        out.writeString(taskDescription);
    }

    public static final Parcelable.Creator<ToDoTask> CREATOR = new Parcelable.Creator<ToDoTask>() {
        public ToDoTask createFromParcel(Parcel in) {
            return new ToDoTask(in);
        }
        public ToDoTask[] newArray(int size) {
            return new ToDoTask[size];
        }
    };

}
