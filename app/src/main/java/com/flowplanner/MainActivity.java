package com.flowplanner;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    private PlanListItemAdapter planListItemAdapter;
    List<PlanEntity> list;

    private NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.addTaskFloatingActionButton);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        refreshData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
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

    public void showNotification(){

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
        RemoteViews remoteViewsExpanded = new RemoteViews(getPackageName(), R.layout.notification_expanded);

        Intent clickIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, clickIntent,0);

        remoteViewsExpanded.setOnClickPendingIntent(R.id.imageView_expanded, pendingIntent);

        Notification notification = new NotificationCompat.Builder(this, "FlowPlanner")
                .setSmallIcon(R.drawable.plus_icon_32)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViewsExpanded)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();

        notificationManagerCompat.notify(1, notification);
    }

}
