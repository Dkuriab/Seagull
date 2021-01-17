package com.seagull.mapper

import com.seagull.data.model.DBRecipe
import com.seagull.data.model.Recipe

class RecipeMapper : MapperInterface<DBRecipe, Recipe> {
    override fun transform(type: DBRecipe): Recipe {
        return Recipe(
            type.link,
            type.name,
            type.id
        )
    }

    override fun transformToDB(type: Recipe): DBRecipe {
        return DBRecipe(
            type.link,
            type.name,
            type.id
        )
    }
}