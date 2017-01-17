package com.jenny.myhome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jenny.database.Project;
import com.jenny.myhome.databinding.ActivityHomeSummaryBinding;

public class HomeSummaryActivity extends AppCompatActivity {
    private ActivityHomeSummaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home_summary);
    }

    @Override
    public void onResume() {
        super.onResume();

        int projectId = getIntent().getIntExtra(Constants.PROJECT_ID, 0);
        if (projectId > 0) {
            Project project = MyHomeApplication.getDatabase().getProject(projectId);
            this.binding.setProject(project);
            /*
            List<RoomSummary> sj = new ArrayList<>();
            for (Room room :
                    project.getRooms()) {
                sj.add(new RoomSummary(room.getSubjects(), room));
            }
            */
        } else {
            finish();
        }
    }

    public void onNewRoomClick(View v) {
        Intent intent = new Intent(v.getContext(), HomeActivity.class);
        intent.putExtra(Constants.PROJECT_ID, this.binding.getProject().getId());
        startActivity(intent);
    }
}
