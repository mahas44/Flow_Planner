package com.flowplanner;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {PlanEntity.class, JobEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlanDao planDao();
    public abstract JobDao jobDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"FlowPlanner")
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
