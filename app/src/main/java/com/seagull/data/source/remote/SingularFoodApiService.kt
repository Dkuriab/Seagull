package com.seagull.data.source.remote

import com.seagull.data.model.DBRecipe
import retrofit2.Call
import retrofit2.http.*

interface SingularFoodApiService {
    @GET("recipe")
    suspend fun getAllRecipes(): Call<List<DBRecipe>>
}