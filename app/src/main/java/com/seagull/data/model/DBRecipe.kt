package com.seagull.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class DBRecipe(
    @ColumnInfo(name = "link") val link: String,
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey(autoGenerate = false) var id: Int = 0
)