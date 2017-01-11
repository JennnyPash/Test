package com.jenny.database;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

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
}