package com.jenny.myhome;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenny.database.Project;
import com.jenny.database.Room;
import com.jenny.myhome.databinding.ActivityRoomBinding;

public class RoomActivity extends AppCompatActivity {
    private ActivityRoomBinding binding;
    private int projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_room);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.projectId = getIntent().getIntExtra(Constants.PROJECT_ID, 0);

        Room room = new Room();
        int roomId = getIntent().getIntExtra(Constants.ROOM_ID, 0);
        if (roomId > 0) {
            room = MyHomeApplication.getDatabase().getRoom(roomId);
        } else {
			if (projectId > 0) {
				Project project = MyHomeApplication.getDatabase().getProject(projectId);
				room.setProject(project);
			}

            RoomType roomType = (RoomType)getIntent().getSerializableExtra(Constants.ROOM_TYPE);
            room.setRoomType(roomType);
        }

        this.binding.setRoom(room);
    }
}
