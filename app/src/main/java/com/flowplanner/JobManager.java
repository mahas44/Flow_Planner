package com.flowplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JobManager extends AppCompatActivity {

    EditText jobNameET, startTimeET, deadlineET;
    Button saveBtn;

    String planName = "planName";
    String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_manager);


        pName = getIntent().getStringExtra(planName);


        jobNameET = findViewById(R.id.jobNameET);
        startTimeET = findViewById(R.id.startTimeET);
        deadlineET = findViewById(R.id.deadlineET);
        saveBtn = findViewById(R.id.saveButtonJob);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobName = jobNameET.getText().toString();
                String startTime = startTimeET.getText().toString();
                String deadline = deadlineET.getText().toString();

                JobEntity job = new JobEntity(pName,jobName,startTime,deadline);

                AppDatabase.getDatabase(getApplicationContext()).jobDao().insert(job);


            }
        });

    }
}
