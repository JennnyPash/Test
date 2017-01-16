package com.jenny.myhome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jenny.binding.ProjectsList;
import com.jenny.database.Project;
import com.jenny.myhome.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Button newProjectButton = (Button)findViewById(R.id.button_new_project);
        newProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Project project = new Project();
                MyHomeApplication.getDatabase().create(project);

                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                intent.putExtra(Constants.PROJECT_ID, project.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Project> projects = MyHomeApplication.getDatabase().getAllProjects();

        if (projects.size() == 0) {
            MyHomeApplication.getDatabase().create(new Project());
            projects = MyHomeApplication.getDatabase().getAllProjects();
        }

        this.binding.setProjectsList(new ProjectsList(projects));
    }
}
