package com.codepath.seaworldtodo.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.codepath.seaworldtodo.R;
import com.codepath.seaworldtodo.model.TodoItemsModel;

public class EditItemActivity extends AppCompatActivity {
    private Spinner spShows;
    private TimePicker timePicker1;
    public static String selectedShow;
    public static String time;
    public String format = "";
    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        //get title from intent
        Bundle getTitle = getIntent().getExtras();
        title = getTitle.getString("title");

        //setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tbEditItem);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Spinner for Show / Rides
        spShows = (Spinner) findViewById(R.id.spShows);
        ArrayAdapter showsAdapter = ArrayAdapter.createFromResource(this,R.array.Shows,android.R.layout.simple_list_item_1);
        spShows.setAdapter(showsAdapter);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.miSave:
                saveShow();
                return true;
            case R.id.miRemoveItem:
                callAlertMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveShow() {
        selectedShow = spShows.getSelectedItem().toString();
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        time =  showTime(hour, min);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedShow",selectedShow);
        returnIntent.putExtra("time", time);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public String showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        return(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format).toString());
    }
    private void callAlertMessage() {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure to Remove?");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        TodoItemsModel removeItems = new TodoItemsModel();
                        removeItems.delete();
                        Intent intent = new Intent(getApplicationContext(), CreateTodoItemActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
