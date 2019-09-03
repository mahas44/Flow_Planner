package com.flowplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class PlanManager extends AppCompatActivity{

    EditText planNameET, descriptionET, categoryET;
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_manager);

        planNameET = findViewById(R.id.planNameET);
        categoryET = findViewById(R.id.categoryET);
        descriptionET = findViewById(R.id.descriptionET);
        saveBtn = findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String planName = planNameET.getText().toString();
                String category = categoryET.getText().toString();
                String description = descriptionET.getText().toString();

                PlanEntity plan = new PlanEntity(planName,description,category);

                AppDatabase.getDatabase(getApplicationContext()).planDao().insert(plan);
            }
        });

    }



}
