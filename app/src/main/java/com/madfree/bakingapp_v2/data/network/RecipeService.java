package com.madfree.bakingapp_v2.data.network;

import com.madfree.bakingapp_v2.data.entity.RecipeEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<RecipeEntity>> getAllRecipes();

}
