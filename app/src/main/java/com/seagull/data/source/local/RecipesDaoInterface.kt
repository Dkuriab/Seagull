package com.seagull.data.source.local

import androidx.room.*
import com.seagull.data.model.DBRecipe

@Dao
interface RecipesDaoInterface {
    @Query("SELECT * FROM recipes")
    suspend fun getAll(): List<DBRecipe>

    @Query("DELETE FROM recipes")
    suspend fun clear()

    @Insert
    suspend fun insertAll(vararg recipes: DBRecipe)

    @Delete
    suspend fun delete(recipe: DBRecipe)

    @Query("SELECT * FROM recipes WHERE id=:id")
    suspend fun loadById(id: Int): DBRecipe?
}