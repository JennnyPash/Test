package com.jenny.binding;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jenny.database.Project;
import com.jenny.database.Subject;
import com.jenny.myhome.Constants;
import com.jenny.myhome.HomeActivity;

/**
 * Created by JennyPash on 1/14/2017.
 */

public class BindAdapters {
    @BindingAdapter("android:text")
    public static void setText(EditText editText, double value) {
        double currentVal;
        try {
            currentVal = Double.parseDouble(editText.getText().toString());
        } catch (Exception e) {
            currentVal = 0d;
        }

        if (currentVal != value) {
            editText.setText(String.format("%.2f",value));
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static double getDouble(EditText view) {
        double value;
        try {
            value = Double.parseDouble(view.getText().toString());
        } catch (Exception e) {
            value = 0d;
        }

        return value;
    }

    @BindingAdapter("android:text")
    public static void setText(EditText editText, int value) {
        int currentVal;
        try {
            currentVal = Integer.parseInt(editText.getText().toString());
        } catch (Exception e) {
            currentVal = 0;
        }

        if (currentVal != value) {
            editText.setText(String.valueOf(value));
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getInt(EditText view) {
        int value;
        try {
            value = Integer.parseInt(view.getText().toString());
        } catch (Exception e) {
            value = 0;
        }

        return value;
    }

    @BindingAdapter("app:items")
    public static void bindProjects(ListView listView, ObservableArrayList<Project> projects) {
        ListAdapter listAdapter = new ArrayAdapter<>(listView.getContext(), android.R.layout.simple_selectable_list_item , projects);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = (Project)parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra(Constants.PROJECT_ID, project.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @BindingAdapter("app:items")
    public static void bindList(ListView view, ObservableArrayList<Subject> list) {
        ListAdapter adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_1, list);
        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subject subject = (Subject)adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(), "SHORT", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subject subject = (Subject)adapterView.getItemAtPosition(i);
                Toast.makeText(view.getContext(), "LONG", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
