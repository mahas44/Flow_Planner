package com.flowplanner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Jobs")
public class JobEntity {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    int id;
    String planName;
    String name;
    String startTime;
    String deadline;


    public JobEntity(String planName, String name, String startTime, String deadline) {
        this.planName = planName;
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
