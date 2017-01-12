package com.jenny.myhome;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jenny.database.Project;
import com.jenny.myhome.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        int projectId = this.getIntent().getIntExtra("projectId", 0);
        if (projectId > 0) {
            this.binding.setProject(MyHomeApplication.getDatabase().getProject(projectId));
        } else {
            this.binding.setProject(new Project());
        }
    }

    public void onRoomClick(View view) {
        RoomType roomType = (RoomType)view.getTag();
    }
}
