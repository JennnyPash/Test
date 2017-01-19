package com.jenny.myhome;

/**
 * Created by JennyPash on 1/11/2017.
 */

public enum SubjectType {
    BED(R.string.bed),
    WARDROBE(R.string.wardrobe),
    COMMODE(R.string.commode),
    HANGER(R.string.hanger),
    TABLE(R.string.table),
    CHAIR(R.string.chair),
    SOFA(R.string.sofa),
    DESK(R.string.desk),
    UNIT(R.string.unit);


    private int resourceId;

    SubjectType(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return MyHomeApplication.getContext().getString(this.resourceId);
    }
}
