package com.seagull.data.source.repository

import android.util.Log
import androidx.room.Room
import com.seagull.MyApplication
import com.seagull.data.model.DBSelectedRecipe
import com.seagull.data.model.Recipe
import com.seagull.data.model.SelectedRecipe
import com.seagull.data.source.local.LocalDatabase
import com.seagull.data.source.remote.SingularFoodApiService
import com.seagull.data.source.remote.retrofit.BASE_URL
import com.seagull.mapper.ListRecipeLocalMapper
import com.seagull.mapper.ListSelectedLocalMapper
import com.seagull.mapper.RecipeMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RecipeRepository {

    private val recipeTransformer = RecipeMapper()
    private val selectedListTransformer = ListSelectedLocalMapper()
    private val recipeListTransformer = ListRecipeLocalMapper()

    private val localDatabase = Room
        .databaseBuilder(
            MyApplication.appContext,
            LocalDatabase::class.java,
            "localDB"
        )
        .build()

    private val retrofitService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(SingularFoodApiService::class.java)


    suspend fun updateSelectedRecipes(selected: DBSelectedRecipe) = withContext(Dispatchers.IO) {
        localDatabase.idSelectedDao().updateSelected(selected)
    }

    suspend fun fillRecipeList(vararg recipes: Recipe) = withContext(Dispatchers.IO) {
        val dbList = recipeListTransformer.transformToDB(recipes.toList())
        localDatabase.recipeDao().insertAll(*dbList.toTypedArray())
    }

    suspend fun getRecipeList(): List<Recipe> = withContext(Dispatchers.IO) {
        return@withContext ListRecipeLocalMapper().transform(localDatabase.recipeDao().getAll())
    }

    suspend fun getSelectedList(): List<SelectedRecipe> = withContext(Dispatchers.IO) {
        return@withContext selectedListTransformer.transform(localDatabase.idSelectedDao().getAll())
    }

    suspend fun getById(id: Int): Recipe? = withContext(Dispatchers.IO) {
        val recipe = localDatabase.recipeDao().loadById(id)
        return@withContext if (recipe != null) {
            recipeTransformer.transform(recipe)
        } else {
            Log.d("Repository", "Cant get recipe by id $id")
            null
        }
    }
}
