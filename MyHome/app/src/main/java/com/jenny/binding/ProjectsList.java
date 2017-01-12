package com.jenny.binding;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jenny.database.Project;

import java.util.List;

/**
 * Created by kivanov on 1/11/2017.
 */

public class ProjectsList {
    private ObservableArrayList<Project> projects = new ObservableArrayList<>();

    public ProjectsList(List<Project> projects) {
        this.projects.addAll(projects);
    }

    public ObservableArrayList<Project> getProjects() {
        return projects;
    }

    @BindingAdapter("app:items")
    public static void bindProjects(ListView listView, ObservableArrayList<Project> projects) {
        ListAdapter listAdapter = new ArrayAdapter<>(listView.getContext(), android.R.layout.simple_selectable_list_item , projects);
        listView.setAdapter(listAdapter);
    }
}
