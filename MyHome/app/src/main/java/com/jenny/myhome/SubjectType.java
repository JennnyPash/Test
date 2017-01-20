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
    UNIT(R.string.unit),
    FRIDGE(R.string.fridge),
    OVEN(R.string.oven),
    DISHWASHER(R.string.dishwasher),
    ABSORBER(R.string.absorber),
    DRYING_MACHINE(R.string.drying_machine),
    TV(R.string.tv),
    CLIMATIC(R.string.climatic),
    COUCH(R.string.couch),
    CARPET(R.string.carpet),
    PARQUET(R.string.parquet),
    TILES(R.string.tiles),
    PORTMANTEAU(R.string.portmanteau),
    SHOWER(R.string.shower),
    BATHTUB(R.string.bathtub),
    SHOWER_CABIN(R.string.shower_cabin),
    SINK(R.string.sink),
    TOILET_SEAT(R.string.toilet_seat),
    MIRROR(R.string.mirror);

    private int resourceId;

    SubjectType(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return MyHomeApplication.getContext().getString(this.resourceId);
    }
}
