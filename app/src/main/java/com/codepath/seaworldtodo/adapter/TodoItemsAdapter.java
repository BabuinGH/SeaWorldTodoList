package com.codepath.seaworldtodo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codepath.seaworldtodo.R;
import com.codepath.seaworldtodo.model.TodoItemsModel;

import java.util.ArrayList;

/**
 * Created by babs on 2/20/17.
 */

public class TodoItemsAdapter extends ArrayAdapter<TodoItemsModel> {
    public TodoItemsAdapter(Context context, ArrayList<TodoItemsModel> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoItemsModel show = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todoitemsmodel, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvShowName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvShowTime);
        // Populate the data into the template view using the data object
        tvName.setText(show.showName);
        tvHome.setText(show.showTime);
        // Return the completed view to render on screen
        return convertView;
    }

}
