package com.madfree.bakingapp_v2.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.madfree.bakingapp_v2.data.entity.RecipeEntity;
import com.madfree.bakingapp_v2.data.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    LiveData<List<RecipeEntity>> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE id=:id")
    LiveData<RecipeEntity> getRecipeById(int id);

    @Query("SELECT count(*) FROM recipes")
    int count();

    @Insert
    void insertAll(RecipeEntity... recipes);

    @Insert
    void insert (RecipeEntity recipe);

    @Delete
    void delete(RecipeEntity recipe);
}
