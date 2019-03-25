package com.madfree.bakingapp_v2.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.madfree.bakingapp_v2.data.entity.StepEntity;

import java.util.List;

@Dao
public interface StepDao {

    @Insert
    void insert(StepEntity step);

    @Update
    void update(StepEntity... steps);

    @Delete
    void delete(StepEntity... steps);

    @Query("SELECT * FROM steps")
    LiveData<List<StepEntity>> getAllSteps();

    @Query("SELECT * FROM steps WHERE recipeId=:recipeId")
    LiveData<List<StepEntity>> getStepsForRecipe(final int recipeId);

    @Query("SELECT * FROM steps WHERE recipeId=:recipeId AND stepId=:stepId")
    List<StepEntity> getStepById(final int recipeId, final int stepId);
}
