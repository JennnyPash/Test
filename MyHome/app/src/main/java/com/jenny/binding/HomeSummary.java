package com.jenny.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.jenny.database.Project;

import java.util.List;

/**
 * Created by JennyPash on 1/17/2017.
 */

public class HomeSummary extends BaseObservable{
    private Project project;
    private ObservableArrayList<RoomSummary> roomSumarries;

    public HomeSummary(Project project, List<RoomSummary> roomSumarries) {
        this.project = project;
        this.roomSumarries = new ObservableArrayList<>();
        if (roomSumarries != null) {
            this.roomSumarries.addAll(roomSumarries);
        }
    }

    @Bindable
    public Project getProject() {
        return project;
    }

    @Bindable
    public List<RoomSummary> getRoomSumarries() {
        return roomSumarries;
    }

    public void removeHomeSummary(RoomSummary roomSummary) {
        this.roomSumarries.remove(roomSummary);
        notifyChange();
    }
}
