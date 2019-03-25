package com.madfree.bakingapp_v2.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.madfree.bakingapp_v2.data.model.Ingredient;
import com.madfree.bakingapp_v2.data.model.Recipe;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "ingredients",
        indices = {@Index(value = "recipeId")},
        foreignKeys = @ForeignKey(entity = RecipeEntity.class,
                parentColumns = "id",
                childColumns = "recipeId",
                onDelete = CASCADE))
public class IngredientEntity implements Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Double quantity;
    private String measure;
    private String ingredient;
    private int recipeId;

    public int getId() {
        return id;
    }

    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public String getMeasure() {
        return measure;
    }

    @Override
    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int getRecipeId() {
        return recipeId;
    }

    public IngredientEntity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Ignore
    public IngredientEntity(Double quantity, String measure, String ingredient,
                            int recipeId) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
        this.recipeId = recipeId;
    }
}
