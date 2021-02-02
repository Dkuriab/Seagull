package com.seagull.mapper

import com.seagull.data.model.DBRecipe
import com.seagull.data.model.Recipe

class ListRecipeLocalMapper : MapperInterface<List<DBRecipe>, List<Recipe>> {
    override fun transform(type: List<DBRecipe>): List<Recipe> {
        return type.map { dbRecipe ->
            Recipe(
                id = dbRecipe.id,
                name = dbRecipe.name,
                image_link_small = dbRecipe.image_link_small,
                image_link_big = dbRecipe.image_link_big,
                link = dbRecipe.link,
                portions = dbRecipe.portions,
                preparing_time = dbRecipe.preparing_time,
                cooking_time = dbRecipe.cooking_time,
                difficulty = dbRecipe.difficulty,
            )
        }
    }

    override fun transformToDB(type: List<Recipe>): List<DBRecipe> {
        return type.map { recipe ->
            DBRecipe(
                id = recipe.id,
                name = recipe.name,
                image_link_small = recipe.image_link_small,
                image_link_big = recipe.image_link_big,
                link = recipe.link,
                portions = recipe.portions,
                preparing_time = recipe.preparing_time,
                cooking_time = recipe.cooking_time,
                difficulty = recipe.difficulty,
            )
        }
    }
}