package com.flowplanner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanDao extends BaseDao<PlanEntity> {

    @Query("SELECT * FROM Plans")
    List<PlanEntity> getAll();

}
