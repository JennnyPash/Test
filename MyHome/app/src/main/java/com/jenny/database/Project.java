package com.jenny.database;

import android.databinding.Bindable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.jenny.myhome.BR;
import com.jenny.myhome.MyHomeApplication;
import com.jenny.myhome.R;

import java.util.Date;

/**
 * Created by JennyPash on 1/11/2017.
 */

public class Project extends Entity {
    @DatabaseField
    private String name;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Room> rooms;

    @DatabaseField
    private Date dateCreated;

    public Project() {
        this.name = "New project";
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public ForeignCollection<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ForeignCollection<Room> rooms) {
        this.rooms = rooms;
        notifyPropertyChanged(BR.rooms);
    }

    @Bindable
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        notifyPropertyChanged(BR.dateCreated);
    }

    @Override
    public String toString() {
        return String.format("%s - %d %s", this.name, this.rooms != null ? this.rooms.size() : 0, MyHomeApplication.getContext().getString(R.string.rooms));
    }
}
