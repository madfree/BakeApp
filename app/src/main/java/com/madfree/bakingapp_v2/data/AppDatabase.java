package com.madfree.bakingapp_v2.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.madfree.bakingapp_v2.data.dao.IngredientDao;
import com.madfree.bakingapp_v2.data.dao.RecipeDao;
import com.madfree.bakingapp_v2.data.dao.StepDao;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.entity.RecipeEntity;
import com.madfree.bakingapp_v2.data.entity.StepEntity;
import com.madfree.bakingapp_v2.data.model.Ingredient;
import com.madfree.bakingapp_v2.data.model.Recipe;
import com.madfree.bakingapp_v2.data.model.Step;
import com.madfree.bakingapp_v2.data.network.RecipeService;
import com.madfree.bakingapp_v2.data.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

@Database(entities = {RecipeEntity.class, IngredientEntity.class, StepEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "recipes_db";
    private static AppDatabase sInstance;

    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract StepDao stepDao();

    public static AppDatabase getsInstance(Context context) {

        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    private static RoomDatabase.Callback roomCallback = new AppDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            fetchRecipeData();
            Log.d(LOG_TAG, "roomCallback starting to set up database");
        }
    };

    private static void fetchRecipeData() {
        final RecipeService recipeService = RetrofitInstance.getsInstance();
        Log.d(LOG_TAG, "Calling RecipeService");

        recipeService.getAllRecipes().enqueue(new retrofit2.Callback<List<RecipeEntity>>() {
            @Override
            public void onResponse(Call<List<RecipeEntity>> call, Response<List<RecipeEntity>> response) {
                Integer statusCode = response.code();
                Log.d(LOG_TAG, "Status Code: " + statusCode.toString());
                List<RecipeEntity> recipeList = response.body();
                new PopulateDbAsync(sInstance).execute(recipeList);
            }

            @Override
            public void onFailure(Call<List<RecipeEntity>> call, Throwable t) {
                Log.d(LOG_TAG, "Error!" + t.getMessage());
            }
        });
    }

    private static class PopulateDbAsync extends AsyncTask<List<RecipeEntity>, Void, Void> {

        private final RecipeDao recipeDao;
        private final IngredientDao ingredientDao;
        private final StepDao stepDao;

        PopulateDbAsync(AppDatabase db) {
            recipeDao = db.recipeDao();
            ingredientDao = db.ingredientDao();
            stepDao = db.stepDao();
        }

        @Override
        protected Void doInBackground(List<RecipeEntity>... lists) {
            sInstance.clearAllTables();
            Log.d(LOG_TAG, "Cleared the tables");

            for (int i = 0; i < lists[0].size(); i++) {
                String recipeName = lists[0].get(i).getName();
                int recipeId = lists[0].get(i).getId();
                int servings = lists[0].get(i).getServings();
                String imageUrl = lists[0].get(i).getImage();
                RecipeEntity newRecipe = new RecipeEntity(recipeId, recipeName, servings, imageUrl);
                recipeDao.insert(newRecipe);
                Log.d(LOG_TAG, "Inserted recipe: " + recipeName);

                List<IngredientEntity> ingredientList = lists[0].get(i).getIngredients();
                for (int j = 0; j < ingredientList.size(); j++) {
                    String ingredientName = ingredientList.get(j).getIngredient();
                    String ingredientMeasure = ingredientList.get(j).getMeasure();
                    Double ingredientQuantity = ingredientList.get(j).getQuantity();
                    IngredientEntity ingredient = new IngredientEntity(ingredientQuantity, ingredientMeasure,
                            ingredientName, recipeId);
                    ingredientDao.insert(ingredient);
                    Log.d(LOG_TAG, "Inserted ingredient: " + ingredientName);
                }

                List<StepEntity> stepList = lists[0].get(i).getSteps();
                for (int k = 0; k < stepList.size(); k++) {
                    int stepId = stepList.get(k).getId();
                    String stepShortDesc = stepList.get(k).getShortDescription();
                    String stepDesc = stepList.get(k).getDescription();
                    String stepVideoUrl = stepList.get(k).getVideoURL();
                    String stepThumbNailUrl = stepList.get(k).getThumbnailURL();
                    StepEntity step = new StepEntity(stepId, stepShortDesc, stepDesc, stepVideoUrl,
                            stepThumbNailUrl, recipeId);
                    stepDao.insert(step);
                    Log.d(LOG_TAG, "Inserted step: " + stepShortDesc);
                }
            }
            return null;
        }
    }
}
