package com.seagull.ui.daily.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.misc.slideDown
import com.seagull.misc.slideUp
import com.seagull.ui.MainActivity.Companion.bottomNavigationView

abstract class AbstractListFragment : Fragment() {
    protected lateinit var viewManager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewManager = StaggeredGridLayoutManager(2, VERTICAL)
        viewManager.gapStrategy = GAP_HANDLING_NONE
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        setTransition(view)
        return view
    }

    protected val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0 && bottomNavigationView.isShown) {
                slideDown(bottomNavigationView)
                bottomNavigationView.menu.forEach { it.isEnabled = false }
            } else if (dy < 0) {
                slideUp(bottomNavigationView)
                bottomNavigationView.menu.forEach { it.isEnabled = true }
            }
        }
    }

    protected fun onRecipeClicked(
        position: Int,
        recyclerView: RecyclerView,
        directions: NavDirections,
        cardView: View,
        PhotoDestinationTransitionName: String,
        DestinationTransitionName: String,
        NameDestinationTransitionName: String
    ) {
        val extras = FragmentNavigatorExtras(
            cardView to DestinationTransitionName
        )

        findNavController().navigate(directions, extras)
    }

    private fun setTransition(view: View) {
        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }

        exitTransition = Hold()
        reenterTransition = MaterialElevationScale(true)

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }
}


