package com.madfree.bakingapp_v2.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madfree.bakingapp_v2.R;
import com.madfree.bakingapp_v2.data.entity.IngredientEntity;
import com.madfree.bakingapp_v2.data.model.Ingredient;

import java.util.List;

import static java.lang.String.valueOf;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    public static final String LOG_TAG = IngredientAdapter.class.getSimpleName();

    private List<IngredientEntity> mIngredientsList;

    public IngredientAdapter() {
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.ingredient_list_item, viewGroup, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder ingredientViewHolder, int position) {
        ingredientViewHolder.ingredientQuantityTextView.setText(valueOf(mIngredientsList.get(position).getQuantity()));
        //Log.d(LOG_TAG, "Getting the measure: " + mIngredientsList.get(position).getQuantity());
        ingredientViewHolder.ingredientQuantityTextView.setText(mIngredientsList.get(position).getMeasure());
        ingredientViewHolder.ingredientQuantityTextView.setText(mIngredientsList.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        if (mIngredientsList == null) {
            return 0;
        }
        return mIngredientsList.size();

    }

    public void setIngredients(List<IngredientEntity> ingredientList) {
        this.mIngredientsList = ingredientList;
        Log.d(LOG_TAG, "number of ingredients in adapter is: " + mIngredientsList.size());
        notifyDataSetChanged();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        final TextView ingredientQuantityTextView;
        final TextView ingredientMeasureTextView;
        final TextView ingredientInfoTextView;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientQuantityTextView = itemView.findViewById(R.id.ingredients_quantity);
            ingredientMeasureTextView = itemView.findViewById(R.id.ingredients_measure);
            ingredientInfoTextView = itemView.findViewById(R.id.ingredients_text);
        }
    }
}
