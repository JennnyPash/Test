package com.jenny.database;

import android.databinding.Bindable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.jenny.myhome.BR;
import com.jenny.myhome.RoomType;

/**
 * Created by JennyPash on 1/11/2017.
 */

public class Room extends Entity {
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Project project;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Subject> subjects;

    @DatabaseField
    private double budget;

    @DatabaseField
    private RoomType roomType;

    @Bindable
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        notifyPropertyChanged(BR.project);
    }

    @Bindable
    public ForeignCollection<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ForeignCollection<Subject> subjects) {
        this.subjects = subjects;
        notifyPropertyChanged(BR.subjects);
    }

    @Bindable
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
        notifyPropertyChanged(BR.budget);
    }

    @Bindable
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
        notifyPropertyChanged(BR.roomType);
    }

    @Override
    public String toString() {
        return this.roomType.toString();
    }
}
