package com.seagull.ui.daily.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.data.model.Recipe
import com.seagull.ui.daily.SelectedRecipeViewModel
import kotlinx.android.synthetic.main.daily_fragment.view.*
import kotlinx.android.synthetic.main.recipe_preview.view.*
import listBreakfast

class BreakfastListFragment : AbstractListFragment() {

    private val model: SelectedRecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val tabPosition = model.tabPosition.value ?: 0

        val view = super.onCreateView(inflater, container, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.addOnScrollListener(scrollListener)
        recyclerView.apply {
            layoutManager = viewManager
            adapter =
                RecipeListAdapter(
                    listBreakfast,
                    { position: Int, view: View, recipe: Recipe ->
                        onRecipeClicked(
                            position,
                            recyclerView,
                            BreakfastListFragmentDirections.actionBreakfastListFragmentToRecipeFragment(
                                recipe.link,
                                recipe.name
                            ),
                            view,
                            getString(R.string.recipe_photo_transition_name),
                            getString(R.string.recipe_transition_name),
                            getString(R.string.recipe_name_transition_name)
                        )
                    },
                    { view: View, recipe: Recipe ->
                        model.breakfastIdList[tabPosition].value = recipe
                        view.selected_point.visibility = View.VISIBLE
                    },
                    { view: View, recipe: Recipe ->
                        model.breakfastIdList[tabPosition].observe(viewLifecycleOwner, {
                            if (recipe.id == it.id) {
                                view.selected_point.visibility = View.VISIBLE
                            } else {
                                view.selected_point.visibility = View.GONE
                            }
                        })
                    }
                )
        }

        return view
    }
}


