package com.seagull.ui.daily.choose

import android.os.Bundle
import android.view.View
import com.seagull.data.model.DateIdentificator
import com.seagull.ui.daily.choose.AbstractListFragment

class DinnerListFragment : AbstractListFragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recycler_view.addOnScrollListener(scrollListener)
//        recycler_view.apply {
//            layoutManager = viewManager
//            adapter =
//                RecipeListAdapter(listBreakfast) { position: Int, view: View, recipe: Recipe ->
//                    onRecipeClicked(
//                        position,
//                        recycler_view,
//                        DinnerListFragmentDirections.actionDinnerListFragmentToRecipeFragment(
//                            recipe.name,
//                            recipe.link
//                        ),
//                        view,
//                        getString(R.string.recipe_photo_transition_name),
//                        getString(R.string.recipe_transition_name),
//                        getString(R.string.recipe_name_transition_name)
//                    )
//                }
//        }
    }
}