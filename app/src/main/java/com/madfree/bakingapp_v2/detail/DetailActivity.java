package com.madfree.bakingapp_v2.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.madfree.bakingapp_v2.R;

public class DetailActivity extends AppCompatActivity {

    public static final String LOG_TAG = DetailActivity.class.getSimpleName();
    public static final String RECIPE_ID = "RECIPE_ID";
    private int recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        recipeId = intent.getIntExtra(RECIPE_ID, 0);
        Log.d(LOG_TAG, "Starting DetailActivity with this recipeId: " + recipeId);

        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Starting to draw contentView");
        setContentView(R.layout.activity_detail);


    }
}
