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
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MyHomeApplication.getDatabase().getAllProjects().size() == 0) {
            MyHomeApplication.getDatabase().create(new Project());
        }

        this.binding.setProjectsList(new ProjectsList(MyHomeApplication.getDatabase().getAllProjects()));
    }
}
