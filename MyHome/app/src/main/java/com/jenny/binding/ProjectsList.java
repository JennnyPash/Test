package com.jenny.binding;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jenny.database.Project;
import com.jenny.myhome.HomeActivity;

import java.util.List;

/**
 * Created by JennyPash on 1/11/2017.
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = (Project)parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra("projectId", project.getId());
                view.getContext().startActivity(intent);
            }
        });
    }
}
