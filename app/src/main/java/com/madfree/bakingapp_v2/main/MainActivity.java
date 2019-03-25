package com.madfree.bakingapp_v2.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.madfree.bakingapp_v2.R;
import com.madfree.bakingapp_v2.data.AppDatabase;
import com.madfree.bakingapp_v2.data.entity.RecipeEntity;
import com.madfree.bakingapp_v2.data.model.Recipe;
import com.madfree.bakingapp_v2.detail.DetailActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipesListAdapter.ItemClickListener {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private LiveData<List<RecipeEntity>> mRecipesList;
    private RecyclerView mRecyclerView;
    private RecipesListAdapter mAdapter;

    public static final String RECIPE_ID = "RECIPE_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Testing Database functionality
        final AppDatabase mDb = AppDatabase.getsInstance(getApplicationContext());


        mRecyclerView = findViewById(R.id.recipe_recycler_view);
        mAdapter = new RecipesListAdapter(this, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mDb.recipeDao().getAllRecipes().observe(this, new Observer<List<RecipeEntity>>() {
            @Override
            public void onChanged(@Nullable List<RecipeEntity> recipeEntities) {
                mAdapter.setRecipes(recipeEntities);
            }
        });
    }

    @Override
    public void onItemClickListener(Recipe clickedRecipe) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(RECIPE_ID, clickedRecipe.getId());
        startActivity(intent);
        Log.d(LOG_TAG, "Starting DetailActivity with Recipe: " + clickedRecipe.getName());
    }
}
