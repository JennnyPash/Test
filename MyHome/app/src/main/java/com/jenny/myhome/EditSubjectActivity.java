package com.jenny.myhome;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenny.database.Subject;
import com.jenny.myhome.databinding.ActivityEditSubjectBinding;

public class EditSubjectActivity extends AppCompatActivity {
    private ActivityEditSubjectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_subject);
    }

    @Override
    public void onResume() {
        super.onResume();

        int subjectId = getIntent().getIntExtra(Constants.SUBJECT_ID, 0);
        if (subjectId > 0) {
            Subject subject = MyHomeApplication.getDatabase().getSubject(subjectId);
            this.binding.setSubject(subject);
        } else {
            finish();
        }
    }
}
