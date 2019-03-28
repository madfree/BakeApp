package com.madfree.bakingapp_v2.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
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
import com.madfree.bakingapp_v2.data.Repository;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.entity.StepEntity;
import com.madfree.bakingapp_v2.data.model.Ingredient;
import com.madfree.bakingapp_v2.main.RecipesListAdapter;
import com.madfree.bakingapp_v2.utils.AppExecutors;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String RECIPE_ID = "RECIPE_ID";

    private SharedViewModel sharedViewModel;
    private int recipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Starting to draw contentView");
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        recipeId = intent.getIntExtra(RECIPE_ID, 0);
        Log.d(LOG_TAG, "Starting DetailActivity with this recipeId: " + recipeId);

        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        sharedViewModel.setRecipeId(recipeId);

        DetailListsFragment detailListFragment = new DetailListsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("recipeId", recipeId);
        detailListFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_detail_container, detailListFragment)
                .commit();
    }
}
