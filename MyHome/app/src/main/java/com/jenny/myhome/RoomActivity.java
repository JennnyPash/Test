package com.jenny.myhome;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jenny.binding.SubjectsList;
import com.jenny.database.Project;
import com.jenny.database.Room;
import com.jenny.database.Subject;
import com.jenny.myhome.databinding.ActivityRoomBinding;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {
    private ActivityRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_room);

        initializeSubjectsSpinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int roomId = getIntent().getIntExtra(Constants.ROOM_ID, 0);

        if (roomId > 0) {
            Room room = MyHomeApplication.getDatabase().getRoom(roomId);

            this.binding.setRoom(room);
            this.binding.setSubject(new Subject());
            this.binding.setSubjectsList(new SubjectsList(room.getSubjects()));
        } else {
            finish();
        }
    }

    public void onAddSubjectClick(View v){
        Spinner spinner = (Spinner)findViewById(R.id.subjects_spinner);
        SubjectType subjectType = (SubjectType) spinner.getSelectedItem();

        this.binding.getSubject().setSubjectType(subjectType);
        this.binding.getSubject().setRoom(this.binding.getRoom());

        if (MyHomeApplication.getDatabase().create(this.binding.getSubject()) > 0) {
            this.binding.getSubjectsList().subjects.add(this.binding.getSubject());
            this.binding.setSubject(new Subject());
        }
    }

    private void initializeSubjectsSpinner() {
        ArrayList<SubjectType> arrayList = new ArrayList<>();
        for (SubjectType subjectType : SubjectType.values()) {
            arrayList.add(subjectType);
        }

        Spinner spinner = (Spinner)findViewById(R.id.subjects_spinner);

        ArrayAdapter<SubjectType> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        spinner.setAdapter(arrayAdapter);
    }
}
