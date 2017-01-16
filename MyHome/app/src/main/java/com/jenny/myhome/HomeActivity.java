package com.jenny.myhome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import com.jenny.database.Room;
import com.jenny.myhome.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }

    @Override
    public void onResume() {
        super.onResume();

        int projectId = this.getIntent().getIntExtra(Constants.PROJECT_ID , 0);
        if (projectId > 0) {
            this.binding.setProject(MyHomeApplication.getDatabase().getProject(projectId));
        } else {
            finish();
        }
    }

    public void onRoomClick(View view) {
        RoomType roomType = (RoomType)view.getTag();

        Room room = new Room();
        room.setProject(this.binding.getProject());
        room.setRoomType(roomType);

        MyHomeApplication.getDatabase().create(room);

        Intent intent = new Intent(this, RoomActivity.class);
        intent.putExtra(Constants.ROOM_ID, room.getId());
        startActivity(intent);
    }

    public void onProjectNameClick(View view) {
        Prompts p = new Prompts(this, getString(R.string.project_name), binding.getProject().getName());
        p.setOnOkListener(new Prompts.OnOkListener() {
            @Override
            public void onOkClick(String projectName) {
                binding.getProject().setName(projectName);
                MyHomeApplication.getDatabase().update(binding.getProject());
            }
        });
        p.show();
    }
}
