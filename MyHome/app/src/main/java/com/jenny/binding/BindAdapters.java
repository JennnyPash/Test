package com.jenny.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableArrayList;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jenny.database.Project;
import com.jenny.database.Subject;

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
        ListAdapter listAdapter = new EntityArrayAdapter(listView.getContext(), android.R.layout.simple_selectable_list_item , projects);
        listView.setAdapter(listAdapter);
    }

    @BindingAdapter("app:items")
    public static void bindList(ListView view, ObservableArrayList<Subject> list) {
        ListAdapter adapter = new EntityArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("android:textColor")
    public static void setText(TextView textView, double budgetLeft) {
        switch (Double.compare(budgetLeft, 0)) {
            case -1:
                textView.setTextColor(Color.RED);
                break;
            case 1:
                textView.setTextColor(Color.GREEN);
                break;
            default:
                textView.setTextColor(Color.BLACK);
                break;
        }
    }
}
