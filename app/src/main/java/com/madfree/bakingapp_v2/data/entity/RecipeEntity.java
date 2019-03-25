package com.madfree.bakingapp_v2.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.madfree.bakingapp_v2.data.model.Ingredient;
import com.madfree.bakingapp_v2.data.model.Recipe;
import com.madfree.bakingapp_v2.data.model.Step;

import java.util.List;

@Entity(tableName = "recipes")
public class RecipeEntity implements Recipe {

    @PrimaryKey
    private int id;
    private String name;
    private int servings;
    private String image;

    @Ignore
    @Expose
    private List<IngredientEntity> ingredients = null;

    @Ignore
    @Expose
    private List<StepEntity> steps = null;


    public RecipeEntity(int id, String name, int servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public RecipeEntity(Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.servings = recipe.getServings();
        this.image = recipe.getImage();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getServings() {
        return servings;
    }

    @Override
    public String getImage() {
        return image;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public List<StepEntity> getSteps() {
        return steps;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
