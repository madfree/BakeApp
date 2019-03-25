package com.madfree.bakingapp_v2.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.madfree.bakingapp_v2.data.model.Recipe;
import com.madfree.bakingapp_v2.data.model.Step;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "steps",
        indices = {@Index(value = "recipeId")},
        foreignKeys = @ForeignKey(entity = RecipeEntity.class,
                parentColumns = "id",
                childColumns = "recipeId",
                onDelete = CASCADE))
public class StepEntity implements Step {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int stepId;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    private int recipeId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getStepId() {
        return stepId;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getVideoURL() {
        return videoURL;
    }

    @Override
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    @Override
    public int getRecipeId() {
        return recipeId;
    }

    public StepEntity() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Ignore
    public StepEntity(int stepId, String shortDescription, String description,
                      String videoURL, String thumbnailURL, int recipeId) {
        this.stepId = stepId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.recipeId = recipeId;
    }
}
