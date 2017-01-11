package com.jenny.database;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by kivanov on 1/11/2017.
 */

public class Entity {
    @DatabaseField(generatedId = true)
    private int id;

    public int getId() {
        return id;
    }
}
