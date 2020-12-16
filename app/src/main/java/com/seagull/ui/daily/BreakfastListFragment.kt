package com.seagull.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.data.model.Recipe
import com.seagull.listBreakfast
import com.seagull.ui.MainActivity
import com.seagull.misc.slideDown
import com.seagull.misc.slideUp
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.recipe_preview.view.*

class BreakfastListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewManager = StaggeredGridLayoutManager(2, VERTICAL)
        viewManager.gapStrategy = GAP_HANDLING_NONE

        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }

        recycler_view.apply {
            layoutManager = viewManager
            adapter = RecipeListAdapter(listBreakfast) { view: View, recipe: Recipe ->
                //TODO make navigate with information about our recipe
                onRecipeClicked(
                    view,
                    recipe,
//                    getString(R.string.recipe_photo_transition_name),
                    getString(R.string.recipe_transition_name),
//                    getString(R.string.recipe_name_transition_name)
                )
            }
        }
        val bottomNavigationView = MainActivity.bottomNavigationView

        val scrollListener = object : RecyclerView.OnScrollListener() {
            //TODO return to normal position if it returns to main activity
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && bottomNavigationView.isShown) {
                    slideDown(bottomNavigationView)
                } else if (dy < 0) {
                    slideUp(bottomNavigationView)
                }
            }
        }
        recycler_view.addOnScrollListener(scrollListener)
        setTransition()
    }


    private fun onRecipeClicked(
        cardView: View,
        recipe: Recipe,
//        PhotoDestinationTransitionName: String,
        DestinationTransitionName: String,
//        NameDestinationTransitionName: String
    ) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(
                R.integer.reply_motion_duration_large
            ).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(
                R.integer.reply_motion_duration_large
            ).toLong()
        }

        val extras = FragmentNavigatorExtras(
//            cardView.photo to PhotoDestinationTransitionName,
//            cardView.name to NameDestinationTransitionName,
            cardView to DestinationTransitionName
        )
        val directions =
            BreakfastListFragmentDirections.actionBreakfastListFragmentToRecipeFragment(
                recipe.name,
                recipe.link
            )
        findNavController().navigate(directions, extras)

    }

    private fun setTransition() {
        enterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ true
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ false
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }
}


