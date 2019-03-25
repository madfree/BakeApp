package com.madfree.bakingapp_v2.data.model;

public interface Step {

    int getId();
    int getStepId();
    String getShortDescription();
    String getDescription();
    String getVideoURL();
    String getThumbnailURL();
    int getRecipeId();
}
