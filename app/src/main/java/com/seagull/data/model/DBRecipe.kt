package com.seagull.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class DBRecipe(
    @PrimaryKey(autoGenerate = false) var id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_link_small") val image_link_small: String,
    @ColumnInfo(name = "image_link_big") val image_link_big: String,
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "portions") val portions: Int,
    @ColumnInfo(name = "preparing_time") val preparing_time: String,
    @ColumnInfo(name = "cooking_time") val cooking_time: String,
    @ColumnInfo(name = "difficulty") val difficulty: Int
)