package com.jenny.myhome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.jenny.binding.RoomSummary;
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

        ListView listView = (ListView)findViewById(R.id.subjects_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject subject = (Subject)parent.getItemAtPosition(position);

                if (id == R.id.item_text) {
                    Intent intent = new Intent(view.getContext(), EditSubjectActivity.class);
                    intent.putExtra(Constants.SUBJECT_ID, subject.getId());
                    startActivity(intent);
                } else if (id == R.id.delete_item) {
                    if (MyHomeApplication.getDatabase().delete(subject) > 0) {
                        binding.getRoomSummary().removeItem(subject);
                    }
                } else {
                    finish();
                }
            }
        });

        EditText editText = (EditText)findViewById(R.id.budget_edittext);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP) {
                    MyHomeApplication.getDatabase().update(binding.getRoom());
                    binding.getRoomSummary().notifyChange();
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int roomId = getIntent().getIntExtra(Constants.ROOM_ID, 0);

        if (roomId > 0) {
            Room room = MyHomeApplication.getDatabase().getRoom(roomId);

            this.binding.setRoom(room);
            this.binding.setSubject(new Subject());
            this.binding.setRoomSummary(new RoomSummary(room.getSubjects(), room));
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
            this.binding.getRoomSummary().addItem(this.binding.getSubject());
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
