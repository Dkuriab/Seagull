package com.seagull.ui.daily

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.Recipe
import kotlinx.android.synthetic.main.list_fragment.*
import listBreakfast

class BreakfastListFragment : AbstractListFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.addOnScrollListener(scrollListener)
        recyclerView.apply {
            layoutManager = viewManager
            adapter =
                RecipeListAdapter(listBreakfast) { position: Int, view: View, recipe: Recipe ->
                    //                        scrollToPosition(position)
                    onRecipeClicked(
                        position,
                        recycler_view,
                        BreakfastListFragmentDirections.actionBreakfastListFragmentToRecipeFragment(
                            recipe.name,
                            recipe.link
                        ),
                        view,
                        getString(R.string.recipe_photo_transition_name),
                        getString(R.string.recipe_transition_name),
                        getString(R.string.recipe_name_transition_name)
                    )
                }
        }
        return view
    }
}


