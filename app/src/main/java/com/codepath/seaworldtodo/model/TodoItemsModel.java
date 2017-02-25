package com.codepath.seaworldtodo.model;

import com.codepath.seaworldtodo.database.TodoItemDbHelper;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by babs on 2/20/17.
 */
@Table(database = TodoItemDbHelper.class)
public class TodoItemsModel extends BaseModel{
    @Column
    @PrimaryKey
    public String showName;

    @Column
    public String showTime;

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
