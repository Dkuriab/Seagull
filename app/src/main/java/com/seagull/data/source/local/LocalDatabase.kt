package com.seagull.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seagull.data.model.DBRecipe
import com.seagull.data.model.DBSelectedRecipe

@Database(entities = [DBRecipe::class, DBSelectedRecipe::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipesDaoInterface
    abstract fun idSelectedDao(): IdSelectedDaoInterface
}