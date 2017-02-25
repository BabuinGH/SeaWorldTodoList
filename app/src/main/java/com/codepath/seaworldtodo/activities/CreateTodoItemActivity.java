package com.codepath.seaworldtodo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.seaworldtodo.R;
import com.codepath.seaworldtodo.adapter.TodoItemsAdapter;
import com.codepath.seaworldtodo.model.TodoItemsModel;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class CreateTodoItemActivity extends AppCompatActivity {
    ArrayList<TodoItemsModel> arrayOfShows;
    TodoItemsAdapter adapter;
    ListView lvItems;
    private final int REQUEST_CODE = 1;
    public String selectedShow;
    public String selectedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Sets the Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.todo_list, null));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            selectedShow = data.getExtras().getString("selectedShow");
            selectedTime = data.getExtras().getString("time");

            //add the shows to arraylist
            TodoItemsModel addItems = new TodoItemsModel();
            addItems.setShowName(selectedShow);
            addItems.setShowTime(selectedTime);
            addItems.save();

            arrayOfShows = (ArrayList<TodoItemsModel>) SQLite.select().
                    from(TodoItemsModel.class).queryList();
            adapter = new TodoItemsAdapter(this,arrayOfShows);
            adapter.notifyDataSetChanged();

            //Attach listView with adapter
            lvItems = (ListView)findViewById(R.id.lvItems);
            lvItems.setAdapter(adapter);
            lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), EditItemActivity.class);
                    intent.putExtra("title", "Edit Item");
                    startActivity(intent);
                }
            });

        }

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.miAddItem){
            Intent intent = new Intent(this, EditItemActivity.class);
            intent.putExtra("title", "Add Item");
            startActivityForResult(intent, REQUEST_CODE);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
