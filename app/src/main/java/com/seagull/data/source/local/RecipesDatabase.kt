package com.seagull.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seagull.data.model.DBRecipe

@Database(entities = [DBRecipe::class], version = 1, exportSchema = false)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipesDaoInterface
}