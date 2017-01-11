package com.jenny.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
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
}
