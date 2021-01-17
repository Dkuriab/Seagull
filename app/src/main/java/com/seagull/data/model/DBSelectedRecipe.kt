package com.seagull.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date_id_selected_recipe")
data class DBSelectedRecipe(
    @PrimaryKey @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "id") val id: Int,
)