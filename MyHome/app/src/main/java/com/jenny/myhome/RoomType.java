package com.jenny.myhome;

/**
 * Created by JennyPash on 1/11/2017.
 */

public enum RoomType {
    BATH_ROOM(R.string.bath_room),
    KITCHEN(R.string.kitchen),
    LIVING_ROOM(R.string.living_room),
    DINING_ROOM(R.string.dining_room),
    BED_ROOM(R.string.bed_room),
    CABINET(R.string.cabinet);

    private int resourceId;

    RoomType(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return MyHomeApplication.getContext().getString(this.resourceId);
    }
}
