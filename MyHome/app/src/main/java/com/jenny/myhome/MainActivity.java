package com.jenny.myhome;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.jenny.binding.HomeSummary;
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

        ListView listView = (ListView)findViewById(R.id.projects_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = (Project) parent.getItemAtPosition(position);

                if (id == R.id.item_text) {
                    Intent intent = project.getRooms().size() > 0 ? new Intent(view.getContext(), HomeSummaryActivity.class)
                            : new Intent(view.getContext(), HomeActivity.class);
                    intent.putExtra(Constants.PROJECT_ID, project.getId());
                    startActivity(intent);
                } else if (id == R.id.delete_item) {
                    if (MyHomeApplication.getDatabase().delete(project) > 0) {
                        binding.getProjectsList().getProjects().remove(project);
                    }
                } else {
                    finish();
                }
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
