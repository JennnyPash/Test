package com.jenny.binding;

import android.databinding.ObservableArrayList;
import com.jenny.database.Project;

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
}
