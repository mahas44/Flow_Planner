package com.flowplanner;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {

    @Insert
    void insert(T entity);

    @Update
    void update(T entiy);

    @Delete
    void delete(T entity);

}
