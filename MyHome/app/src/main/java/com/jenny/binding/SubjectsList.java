package com.jenny.binding;

import android.databinding.ObservableArrayList;

import com.j256.ormlite.dao.ForeignCollection;
import com.jenny.database.Subject;

/**
 * Created by JennyPash on 1/14/2017.
 */

public class SubjectsList {
    public ObservableArrayList<Subject> subjects;

    public SubjectsList(ForeignCollection<Subject> subjects) {
        this.subjects = new ObservableArrayList<>();
        if (subjects != null) {
            this.subjects.addAll(subjects);
        }
    }
}
