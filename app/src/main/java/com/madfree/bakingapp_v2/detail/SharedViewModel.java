package com.madfree.bakingapp_v2.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.madfree.bakingapp_v2.data.Repository;
import com.madfree.bakingapp_v2.data.model.Ingredient;

import java.util.List;

//public class SharedViewModel extends AndroidViewModel {
//
//    private Repository mRepository;
//    private LiveData<List<? extends Ingredient>> ingredientsForRecipeList;
//    private int recipeId;
//
//    public SharedViewModel(@NonNull Application application) {
//        super(application);
//        mRepository = new Repository(application);
//        ingredientsForRecipeList = mRepository.getIngredientsForRecipe();
//    }
//
//    public LiveData<List<? extends Ingredient>> getIngredientsForRecipeList() {
//        return ingredientsForRecipeList;
//    }
//}
