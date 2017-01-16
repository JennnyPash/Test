package com.jenny.database;

import android.content.Context;
import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.StringBytesType;
import com.jenny.myhome.BR;
import com.jenny.myhome.MyHomeApplication;
import com.jenny.myhome.R;
import com.jenny.myhome.SubjectType;

/**
 * Created by JennyPash on 1/11/2017.
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

    @DatabaseField
    private SubjectType subjectType;

    @Bindable
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
        notifyPropertyChanged(BR.room);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }

    @Bindable
    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
        notifyPropertyChanged(BR.minPrice);
    }

    @Bindable
    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
        notifyPropertyChanged(BR.maxPrice);
    }

    @Bindable
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
        notifyPropertyChanged(BR.subjectType);
    }

    @Override
    public String toString() {
        Context context = MyHomeApplication.getContext();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s: ", this.subjectType));

        if (this.price > 0) {
            stringBuilder.append(String.format(" %.2f %s",
                    this.price, context.getString(R.string.lv)));
        } else {
            stringBuilder.append(String.format("%s %.2f %s %.2f %s",
                    context.getString(R.string.from),
                    this.minPrice,
                    context.getString(R.string.to),
                    this.maxPrice,
                    context.getString(R.string.lv)));
        }

        if (this.url != null && this.url.trim().length() > 0) {
            stringBuilder.append(String.format("\r\n%s", this.url));
        }
        return stringBuilder.toString();
    }
}
