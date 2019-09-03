package com.flowplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.cancel(1);

    }
}
