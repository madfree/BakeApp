package com.madfree.bakingapp_v2.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.madfree.bakingapp_v2.data.entity.IngredientEntity;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(IngredientEntity ingredient);

    @Update
    void update(IngredientEntity... ingredients);

    @Delete
    void delete(IngredientEntity... ingredients);

    @Query("SELECT * FROM ingredients")
    LiveData<List<IngredientEntity>> getAllIngredients();

    @Query("SELECT * FROM ingredients WHERE recipeId=:recipeId")
    LiveData<List<IngredientEntity>> getIngredientsForRecipe(int recipeId);

}
