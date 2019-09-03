package com.flowplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    private PlanListItemAdapter planListItemAdapter;
    private JobListItemAdapter jobListItemAdapter;
    List<PlanEntity> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.addTaskFloatingActionButton);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        refreshData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlanManager.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }

    private void refreshData(){
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        list = db.planDao().getAll();
        if (list != null){
            planListItemAdapter = new PlanListItemAdapter(getApplicationContext(),list);
            //jobListItemAdapter = new JobListItemAdapter(getApplicationContext(),listj);
            recyclerView.setAdapter(planListItemAdapter);
            swipeRefreshLayout.setRefreshing(false);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
