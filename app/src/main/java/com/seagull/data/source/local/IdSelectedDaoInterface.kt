package com.seagull.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.seagull.data.model.DBSelectedRecipe
import com.seagull.data.model.MealTime

@Dao
interface IdSelectedDaoInterface {
    @Query("SELECT * FROM date_id_selected_recipe")
    suspend fun getAll(): List<DBSelectedRecipe>

    @Query("DELETE FROM date_id_selected_recipe")
    suspend fun clear()

    @Insert
    suspend fun insertAll(vararg recipes: DBSelectedRecipe)

    @Delete
    suspend fun delete(recipe: DBSelectedRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSelected(recipe: DBSelectedRecipe)

//    @Query("SELECT * FROM date_id_selected_recipe WHERE date=:date AND mealTime=:mealTime")
//    fun loadSelectedRecipeByDate(date: String, mealTime: MealTime): DBSelectedRecipe?
}