package com.codepath.seaworldtodo.model;

/**
 * Created by babs on 2/20/17.
 */

public class TodoItemsModel {
    public String showName;
    public String showTime;

    //Constructor
    public TodoItemsModel(String name, String time) {
        this.showName = name;
        this.showTime = time;
    }

    //Getters and Setters
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
