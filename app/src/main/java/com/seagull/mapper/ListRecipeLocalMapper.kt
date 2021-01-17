package com.seagull.mapper

import com.seagull.data.model.DBRecipe
import com.seagull.data.model.Recipe

class ListRecipeLocalMapper : MapperInterface<List<DBRecipe>, List<Recipe>> {
    override fun transform(type: List<DBRecipe>): List<Recipe> {
        return type.map { dbRecipe ->
            Recipe(
                link = dbRecipe.link,
                name = dbRecipe.name,
                id = dbRecipe.id
            )
        }
    }

    override fun transformToDB(type: List<Recipe>): List<DBRecipe> {
        return type.map { recipe ->
            DBRecipe(
                link = recipe.link,
                name = recipe.name,
                id = recipe.id
            )
        }
    }
}