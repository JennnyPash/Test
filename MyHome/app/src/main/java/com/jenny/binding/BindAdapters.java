package com.jenny.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.dao.ForeignCollection;
import com.jenny.database.Subject;
import com.jenny.myhome.MyHomeApplication;

/**
 * Created by kivanov on 1/14/2017.
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
    public static void bindList(ListView view, ObservableArrayList<Subject> list) {
        try {
            BaseAdapter adapter = new ArrayAdapter<>(MyHomeApplication.getContext(),
                    android.R.layout.simple_list_item_1, list.toArray());
            view.setAdapter(adapter);
        } catch (Exception e) {
            String eM = e.getMessage();
        }
    }

    @InverseBindingAdapter(attribute = "app:items")
    public static ForeignCollection<Subject> invv(View v) {
        return null;
    }
}
