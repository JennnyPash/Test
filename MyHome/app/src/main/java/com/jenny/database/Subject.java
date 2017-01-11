package com.jenny.database;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by kivanov on 1/11/2017.
 */

public class Subject extends Entity {
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Room room;

    @DatabaseField
    private String url;

    @DatabaseField
    private double minPrice;

    @DatabaseField
    private double maxPrice;

    @DatabaseField
    private double price;
}
