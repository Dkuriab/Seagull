package com.seagull.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seagull.data.model.DBSelectedRecipe

@Database(entities = [DBSelectedRecipe::class], version = 1, exportSchema = false)
abstract class SelectedRecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): IdSelectedDaoInterface
}