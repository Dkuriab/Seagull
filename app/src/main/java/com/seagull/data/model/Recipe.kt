package com.seagull.data.model

data class Recipe(
    var id: Int = 0,
    val name: String,
    val image_link_small: String,
    val image_link_big: String,
    val link: String,
    val portions: Int,
    val preparing_time: String,
    val cooking_time: String,
    val difficulty: Int,
)