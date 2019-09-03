package com.flowplanner;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JobDao extends BaseDao<JobEntity> {

    @Query("SELECT * FROM Jobs")
    List<JobEntity> getAll();

    @Query("SELECT * FROM Jobs WHERE planName=:planName")
    List<JobEntity> getJobsFromPlanName(String planName);
}
