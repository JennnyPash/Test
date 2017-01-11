package com.jenny.myhome;

/**
 * Created by JennyPash on 1/11/2017.
 */

public enum SubjectType {
    BED(R.string.bed);

    private int resourceId;

    SubjectType(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return MyHomeApplication.getContext().getString(this.resourceId);
    }
}
