package com.seagull.mapper

import com.seagull.data.model.DBSelectedRecipe
import com.seagull.data.model.SelectedRecipe

class ListSelectedLocalMapper : MapperInterface<List<DBSelectedRecipe>, List<SelectedRecipe>> {
    override fun transform(type: List<DBSelectedRecipe>): List<SelectedRecipe> {
        return type.map { dbRecipe ->
            SelectedRecipe(
                id = dbRecipe.id,
                date = dbRecipe.date
            )
        }
    }

    override fun transformToDB(type: List<SelectedRecipe>): List<DBSelectedRecipe> {
        return type.map { recipe ->
            DBSelectedRecipe(
                id = recipe.id,
                date = recipe.date
            )
        }
    }
}