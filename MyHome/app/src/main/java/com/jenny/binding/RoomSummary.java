package com.jenny.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import com.j256.ormlite.dao.ForeignCollection;
import com.jenny.database.Room;
import com.jenny.database.Subject;

/**
 * Created by JennyPash on 1/14/2017.
 */

public class RoomSummary extends BaseObservable {
    private ObservableArrayList<Subject> subjects;
    private Room room;

    public RoomSummary(ForeignCollection<Subject> subjects, Room room) {
        this.subjects = new ObservableArrayList<>();
        if (subjects != null) {
            this.subjects.addAll(subjects);
        }
        this.room = room;
    }

    public void addItem(Subject item) {
        this.subjects.add(item);
        notifyChange();
    }

    public void removeItem(Subject item) {
        this.subjects.remove(item);
        notifyChange();
    }

    @Bindable
    public ObservableArrayList<Subject> getSubjects() {
        return subjects;
    }

    @Bindable
    public int getTotalSubjects() {
        return this.subjects.size();
    }

    @Bindable
    public double getBudget() {
        return this.room.getBudget();
    }

    @Bindable
    public double getSum() {
        double sum = 0;

        for (Subject subject : this.subjects) {
            double add = 0d;
            if (subject.getPrice() > 0) {
                add = subject.getPrice();
            } else {
                int divisorCounter = 0;
                if (subject.getMinPrice() > 0) {
                    divisorCounter++;
                    add += subject.getMinPrice();
                }

                if (subject.getMaxPrice() > 0) {
                    divisorCounter++;
                    add += subject.getMaxPrice();
                }

                if (divisorCounter > 0) {
                    add /= divisorCounter;
                }
            }

            sum += add;
        }

        return sum;
    }

    @Bindable
    public double getDiff() {
        return room.getBudget() - this.getSum();
    }
}
