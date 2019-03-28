package com.madfree.bakingapp_v2.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madfree.bakingapp_v2.R;
import com.madfree.bakingapp_v2.data.AppDatabase;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.entity.RecipeEntity;
import com.madfree.bakingapp_v2.data.entity.StepEntity;
import com.madfree.bakingapp_v2.data.model.Step;

import java.util.List;
import java.util.StringJoiner;

public class DetailListsFragment extends Fragment implements StepsListAdapter.ItemClickListener{

    public static final String LOG_TAG = DetailListsFragment.class.getSimpleName();

    private RecyclerView mIngredientsRecyclerView;
    private IngredientAdapter mIngredientsAdapter;

    private RecyclerView mStepsRecyclerView;
    private StepsListAdapter mStepsAdapter;

    private SharedViewModel sharedViewModel;

    public DetailListsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_lists, container, false);

        Bundle bundle = getArguments();
        int recipeId = bundle.getInt("recipeId", 0);
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        mIngredientsRecyclerView = rootView.findViewById(R.id.ingredients_recycler_view);
        mIngredientsAdapter = new IngredientAdapter();
        mIngredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);
        Log.d(LOG_TAG, "Setting up the Views and adapter for the ingredientslist");

//        mStepsRecyclerView = rootView.findViewById(R.id.steps_recycler_view);
//        mStepsAdapter = new StepsListAdapter(this.getActivity(), this);
//        mStepsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        mStepsRecyclerView.setAdapter(mStepsAdapter);

        sharedViewModel.getIngredientsForRecipe(recipeId).observe(this, new Observer<List<IngredientEntity>>() {
            @Override
            public void onChanged(@Nullable List<IngredientEntity> ingredientEntities) {
                mIngredientsAdapter.setIngredients(ingredientEntities);
                Log.d(LOG_TAG, "Returning ingredientList from database with size: " + ingredientEntities.size());
            }
        });

        sharedViewModel.getRecipes().observe(this, new Observer<List<RecipeEntity>>() {
            @Override
            public void onChanged(@Nullable List<RecipeEntity> recipeEntities) {
                Log.d(LOG_TAG, "Testing the Viewmodel, recipes list has size: " + recipeEntities.size());
            }
        });
        return rootView;
    }

    @Override
    public void onItemClickListener(Step clickedStep) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(STEP_ID, clickedStep.getId());
//        startActivity(intent);
        Log.d(LOG_TAG, "Starting StepFragement anew with step: " + clickedStep.getShortDescription());
    }
}
