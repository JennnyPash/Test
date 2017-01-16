package com.jenny.myhome;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jenny.database.Subject;
import com.jenny.myhome.databinding.ActivityEditSubjectBinding;

public class EditSubjectActivity extends AppCompatActivity {
    private ActivityEditSubjectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_subject);

        View saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyHomeApplication.getDatabase().update(binding.getSubject()) > 0) {
                    Toast.makeText(EditSubjectActivity.this, getString(R.string.changes_saved), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        View cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
