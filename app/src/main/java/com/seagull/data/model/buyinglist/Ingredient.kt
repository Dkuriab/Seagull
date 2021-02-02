package com.seagull.data.model.buyinglist

data class Ingredient(
    val id: Int,
    val name: String,
    val weight: Float,
    val type_of_ingredient: MeasureType,
    val recipe_id: Int
)
