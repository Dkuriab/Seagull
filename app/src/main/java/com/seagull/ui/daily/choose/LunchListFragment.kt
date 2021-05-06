package com.seagull.ui.daily.choose

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.MealTime
import com.seagull.data.model.Recipe
import com.seagull.ui.daily.DailyViewModel

class LunchListFragment : AbstractListFragment() {

    private val model: DailyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val selectedDay = model.selectedDay.value ?: 0

        val view = super.onCreateView(inflater, container, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
//        val header = view.findViewById<MaterialTextView>(R.id.meal_time_header)
//        header.text = getString(R.string.lunch)

        recyclerView.addOnScrollListener(scrollListener)

        recyclerView.apply {
            layoutManager = viewManager
            adapter =
                RecipeListAdapter(
                    recipes = listOf(),
                    onClick = { view: View, recipe: Recipe ->
                        onRecipeClicked(
                            LunchListFragmentDirections.actionLunchListFragmentToRecipeFragment(
                                recipe.image_link_small,
                                recipe.name
                            ),
                            view,
                            getString(R.string.recipe_transition_name),
                        )
                    },
                    onLongClick = { recipe: Recipe ->
                        model.select(recipe, MealTime.Lunch)
                    },
                    liveDataListener = { view: View, recipe: Recipe ->
                        model.lunchIdList[selectedDay].observe(viewLifecycleOwner, {
                            val selectedPoint =
                                view.findViewById<ConstraintLayout>(R.id.selected_point)
                            if (recipe.id == it.id) {
                                selectedPoint.visibility = View.VISIBLE
                            } else {
                                selectedPoint.visibility = View.GONE
                            }
                            Log.d("LunchList", "got liveData")
                        })
                    }
                )
        }

        model.recipes.observe(viewLifecycleOwner, {
            val adapter = recyclerView.adapter as RecipeListAdapter
            adapter.putRecipes(it)
        })

        return view
    }
}


