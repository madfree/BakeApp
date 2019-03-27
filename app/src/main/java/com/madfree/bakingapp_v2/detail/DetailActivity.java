package com.madfree.bakingapp_v2.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.madfree.bakingapp_v2.R;
import com.madfree.bakingapp_v2.data.AppDatabase;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.model.Ingredient;
import com.madfree.bakingapp_v2.main.RecipesListAdapter;
import com.madfree.bakingapp_v2.utils.AppExecutors;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String RECIPE_ID = "RECIPE_ID";

    private RecyclerView mRecyclerView;
    private IngredientAdapter mAdapter;

    private LiveData<List<IngredientEntity>> ingredientList;
    private int recipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Starting to draw contentView");
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        recipeId = intent.getIntExtra(RECIPE_ID, 0);
        Log.d(LOG_TAG, "Starting DetailActivity with this recipeId: " + recipeId);

        mRecyclerView = findViewById(R.id.ingredients_recycler_view);
        mAdapter = new IngredientAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        final AppDatabase mDb = AppDatabase.getsInstance(this);
        mDb.ingredientDao().getIngredientsForRecipe(recipeId).observe(this, new Observer<List<IngredientEntity>>() {
                    @Override
                    public void onChanged(@Nullable List<IngredientEntity> ingredientEntities) {
                        mAdapter.setIngredients(ingredientEntities);
                        Log.d(LOG_TAG, "Returning ingredientList from database with size: " + ingredientEntities.size());
                    }
        });

    }
}
