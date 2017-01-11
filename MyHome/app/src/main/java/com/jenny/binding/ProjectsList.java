package com.jenny.binding;

import android.databinding.ObservableArrayList;

import com.jenny.database.Project;

/**
 * Created by kivanov on 1/11/2017.
 */

public class ProjectsList {
    private ObservableArrayList<Project> projects;

    public ProjectsList(ObservableArrayList<Project> projects) {
        this.projects = projects;
    }

    public ObservableArrayList<Project> getProjects() {
        return projects;
    }
}
