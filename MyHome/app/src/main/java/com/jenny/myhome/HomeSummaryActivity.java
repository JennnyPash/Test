package com.jenny.myhome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.jenny.binding.HomeSummary;
import com.jenny.binding.RoomSummary;
import com.jenny.database.Project;
import com.jenny.database.Room;
import com.jenny.myhome.databinding.ActivityHomeSummaryBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeSummaryActivity extends AppCompatActivity {
    private ActivityHomeSummaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home_summary);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.rooms_list_view);
        expandableListView.setOnItemClickListener(new ExpandableListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room room = (Room)parent.getItemAtPosition(position);
                if (id == R.id.edit_room_button) {
                    Intent intent = new Intent(view.getContext(), RoomActivity.class);
                    intent.putExtra(Constants.ROOM_ID, room.getId());
                    startActivity(intent);
                } else if (id == R.id.delete_room_button) {
                    RoomSummary rm = null;
                    for (RoomSummary roomSummary :
                            binding.getHomeSummary().getRoomSumarries()) {
                        if (roomSummary.getRoom().getId() == room.getId()) {
                            rm = roomSummary;
                        }
                    }

                    if (rm != null) {
                        if(MyHomeApplication.getDatabase().delete(room) > 0) {
                            binding.getHomeSummary().removeHomeSummary(rm);
                        }
                    }
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        int projectId = getIntent().getIntExtra(Constants.PROJECT_ID, 0);
        if (projectId > 0) {
            Project project = MyHomeApplication.getDatabase().getProject(projectId);


            List<RoomSummary> roomSummaries = new ArrayList<>();
            for (Room room :
                    project.getRooms()) {
                roomSummaries.add(new RoomSummary(room.getSubjects(), room));
            }
            this.binding.setHomeSummary(new HomeSummary(project, roomSummaries));
        } else {
            finish();
        }
    }

    public void onNewRoomClick(View v) {
        Intent intent = new Intent(v.getContext(), HomeActivity.class);
        intent.putExtra(Constants.PROJECT_ID, this.binding.getHomeSummary().getProject().getId());
        startActivity(intent);
    }
}
