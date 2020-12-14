package com.seagull.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.seagull.MainActivity
import com.seagull.R
import com.seagull.listBreakfast
import com.seagull.navigate
import kotlinx.android.synthetic.main.list_fragment.*

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

        recycler_view.apply {
            layoutManager = viewManager
            adapter = RecipeAdapter(listBreakfast) {
                //TODO make navigate with information about our recipe
                navigate(
                    BreakfastListFragmentDirections.actionBreakfastListFragmentToRecipeFragment(
                        it.name,
                        it.link
                    )
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
    }

}

fun slideUp(view: View) {
    val animate =
        TranslateAnimation(
            0F,
            0F,
            view.height.toFloat(),
            0F
        )
    animate.duration = 300
    animate.fillAfter = true
    view.startAnimation(animate)
}

fun slideDown(view: View) {
    val animate = TranslateAnimation(
        0F,
        0F,
        0F,
        view.height.toFloat()
    )
    animate.duration = 300
    animate.fillAfter = true
    view.startAnimation(animate)
}
