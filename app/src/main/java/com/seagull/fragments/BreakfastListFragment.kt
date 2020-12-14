package com.seagull.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_NONE
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.seagull.R
import com.seagull.listBreakfast
import com.seagull.navigate
import kotlinx.android.synthetic.main.activity_main.*
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
                navigate(BreakfastListFragmentDirections.actionBreakfastListFragmentToRecipeFragment(it.name, it.link))
            }
        }
    }
}