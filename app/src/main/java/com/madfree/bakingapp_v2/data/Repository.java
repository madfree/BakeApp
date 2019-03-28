package com.madfree.bakingapp_v2.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.madfree.bakingapp_v2.data.dao.IngredientDao;
import com.madfree.bakingapp_v2.data.dao.RecipeDao;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.entity.RecipeEntity;
import com.madfree.bakingapp_v2.data.entity.StepEntity;
import com.madfree.bakingapp_v2.data.model.Ingredient;

import java.util.List;

public class Repository {

    private static Repository sInstance;

    private final AppDatabase mDb;
    private MediatorLiveData<List<RecipeEntity>> mObservableRecipes;

    private Repository(final AppDatabase database) {
        mDb = database;
        mObservableRecipes = new MediatorLiveData<>();

        mObservableRecipes.addSource(mDb.recipeDao().getAllRecipes(), recipeEntities -> {
            mObservableRecipes.postValue(recipeEntities);
        });
    }

    public static Repository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (Repository.class) {
                sInstance = new Repository(database);
            }
        }
        return sInstance;
    }

    public LiveData<List<RecipeEntity>> getRecipes() {
        return mObservableRecipes;
    }

    public LiveData<RecipeEntity> loadRecipe(final int recipeId) {
        return mDb.recipeDao().getRecipeById(recipeId);
    }

    public LiveData<List<IngredientEntity>> loadIngredientsForRecipe(final int recipeId) {
        return mDb.ingredientDao().getIngredientsForRecipe(recipeId);
    }

    public LiveData<List<StepEntity>> loadStepsForRecipe(final int recipeId) {
        return mDb.stepDao().getStepsForRecipe(recipeId);
    }

    public LiveData<StepEntity> loadSingleStep(int recipeId, int stepId) {
        return mDb.stepDao().getStepById(recipeId, stepId);
    }


}
