package com.seagull.data.source.repository

import android.util.Log
import androidx.room.Room
import com.seagull.MyApplication
import com.seagull.data.model.DBSelectedRecipe
import com.seagull.data.model.MealTime
import com.seagull.data.model.Recipe
import com.seagull.data.model.SelectedRecipe
import com.seagull.data.source.local.RecipesDatabase
import com.seagull.data.source.local.SelectedRecipeDatabase
import com.seagull.mapper.ListRecipeLocalMapper
import com.seagull.mapper.ListSelectedLocalMapper
import com.seagull.mapper.RecipeMapper

class RecipeRepository {

    private val recipeTransformer = RecipeMapper()

    private val selectedListTransformer = ListSelectedLocalMapper()
    private val selectedDatabase = Room
        .databaseBuilder(
            MyApplication.appContext,
            SelectedRecipeDatabase::class.java,
            "date_id_selected_recipe"
        )
        .build()
        .recipeDao()

    private val recipeListTransformer = ListRecipeLocalMapper()
    private val recipesDatabase = Room
        .databaseBuilder(
            MyApplication.appContext,
            RecipesDatabase::class.java,
            "recipes"
        )
        .build()
        .recipeDao()


    suspend fun updateSelectedRecipes(selected: DBSelectedRecipe) {
        selectedDatabase.updateSelected(selected)
    }

    suspend fun fillRecipeList(vararg recipes: Recipe) {
        val dbList = recipeListTransformer.transformToDB(recipes.toList())
        recipesDatabase.insertAll(*dbList.toTypedArray())
    }

    suspend fun getRecipeList(): List<Recipe> {
        return ListRecipeLocalMapper().transform(recipesDatabase.getAll())
    }

    suspend fun getSelectedList(): List<SelectedRecipe> {
        return selectedListTransformer.transform(selectedDatabase.getAll())
    }

    suspend fun getById(id: Int): Recipe? {
        val recipe = recipesDatabase.loadById(id)
        return if (recipe != null) {
            recipeTransformer.transform(recipe)
        } else {
            Log.d("Repository", "Cant get recipe by id $id")
            null
        }
    }
}
