package com.jenny.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.jenny.database.Project;

import java.util.List;

/**
 * Created by JennyPash on 1/17/2017.
 */

public class HomeSummary extends BaseObservable{
    private Project project;
    private List<RoomSummary> roomSumarries;

    public HomeSummary(Project project, List<RoomSummary> roomSumarries) {
        this.project = project;
        this.roomSumarries = roomSumarries;
    }

    @Bindable
    public Project getProject() {
        return project;
    }

    @Bindable
    public List<RoomSummary> getRoomSumarries() {
        return roomSumarries;
    }
}
