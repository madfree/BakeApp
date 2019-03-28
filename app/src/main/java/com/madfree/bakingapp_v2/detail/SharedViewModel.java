package com.madfree.bakingapp_v2.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;

import com.madfree.bakingapp_v2.data.AppDatabase;
import com.madfree.bakingapp_v2.data.Repository;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.entity.RecipeEntity;
import com.madfree.bakingapp_v2.data.entity.StepEntity;
import com.madfree.bakingapp_v2.data.model.Ingredient;
import com.madfree.bakingapp_v2.data.model.Recipe;
import com.madfree.bakingapp_v2.data.model.Step;

import java.util.List;

public class SharedViewModel extends AndroidViewModel {

    public static final String LOG_TAG = SharedViewModel.class.getSimpleName();

    private MutableLiveData<Integer> selectedRecipe = new MutableLiveData<>();
    private LiveData<List<IngredientEntity>> ingredientsForRecipeList;
    private LiveData<List<RecipeEntity>> allRecipes;
    private LiveData<List<StepEntity>> stepsForRecipeList;
    private LiveData<StepEntity> selectedStep;
    private Repository mRepository;
    AppDatabase mDb;
    private Application application;
    private int recipeId;
    private int stepId = 0;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        mDb = AppDatabase.getsInstance(application);
        Log.d(LOG_TAG, "Setting up the database in ViewModel");
        mRepository = Repository.getInstance(mDb);
        Log.d(LOG_TAG, "Setting up the Repository for the ViewModel");
//        stepsForRecipeList = mRepository.loadStepsForRecipe(recipeId);
//        selectedStep = mRepository.loadSingleStep(recipeId, stepId);
    }

    public LiveData<List<IngredientEntity>> getIngredientsForRecipe(int recipeId) {
        ingredientsForRecipeList = mRepository.loadIngredientsForRecipe(recipeId);
        return ingredientsForRecipeList;
    }

    public LiveData<List<StepEntity>> getStepsForRecipe() {
        return stepsForRecipeList;
    }

    public LiveData<StepEntity> getSingleStep() {
        return selectedStep;
    }

    public LiveData<List<RecipeEntity>> getRecipes() {
        allRecipes = mRepository.getRecipes();
        return allRecipes;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
        Log.d(LOG_TAG, "recipeId in ViewModel set to: " + recipeId);
        ingredientsForRecipeList = mRepository.loadIngredientsForRecipe(recipeId);
//      this.selectedRecipe.setValue(recipeId);

    }
}
