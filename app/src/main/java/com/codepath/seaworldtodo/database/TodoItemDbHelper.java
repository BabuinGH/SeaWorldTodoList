package com.codepath.seaworldtodo.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = TodoItemDbHelper.NAME, version = TodoItemDbHelper.VERSION)
public class TodoItemDbHelper {

    public static final String NAME = "MyDataBase";
    public static final int VERSION = 1;

}
