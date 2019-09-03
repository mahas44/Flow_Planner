package com.flowplanner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Plans")
public class PlanEntity {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String description;
    String category;

    public PlanEntity(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
