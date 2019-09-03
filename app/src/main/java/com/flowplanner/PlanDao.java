package com.flowplanner;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanDao extends BaseDao<PlanEntity> {

    @Query("SELECT * FROM Plans")
    List<PlanEntity> getAll();

    @Query("SELECT * FROM Plans WHERE category=:category")
    List<PlanEntity> getByCategory(String category);

    @Query("SELECT DISTINCT category FROM Plans")
    List<String> getCategories();

}
