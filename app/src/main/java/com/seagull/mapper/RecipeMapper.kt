package com.seagull.mapper

import com.seagull.data.model.DBRecipe
import com.seagull.data.model.Recipe

class RecipeMapper : MapperInterface<DBRecipe, Recipe> {
    override fun transform(type: DBRecipe): Recipe {
        return Recipe(
            id = type.id,
            name = type.name,
            image_link_small = type.image_link_small,
            image_link_big = type.image_link_big,
            link = type.link,
            portions = type.portions,
            preparing_time = type.preparing_time,
            cooking_time = type.cooking_time,
            difficulty = type.difficulty,
        )
    }

    override fun transformToDB(type: Recipe): DBRecipe {
        return DBRecipe(
            id = type.id,
            name = type.name,
            image_link_small = type.image_link_small,
            image_link_big = type.image_link_big,
            link = type.link,
            portions = type.portions,
            preparing_time = type.preparing_time,
            cooking_time = type.cooking_time,
            difficulty = type.difficulty,
        )
    }
}