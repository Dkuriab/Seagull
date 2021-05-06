package com.seagull.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.misc.unHideBottomBar
import java.util.*

class DailyFragment : Fragment() {
    private val model: DailyViewModel by activityViewModels()
//    private var previousPosition = 0 //TODO remove to hell

    private lateinit var tabLayoutDaily: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.fetchRecipeListOnce()
        model.fetchSelectedListOnce()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutDaily = view.findViewById(R.id.tab_layout_daily)
        viewPager = view.findViewById(R.id.view_pager)

        for (i in 0..6) {
            tabLayoutDaily.addTab(
                tabLayoutDaily.newTab().setText(getDate(i).toString())
            )
//            Log.d("Added day: ", getDate(i).toString())
        }

        viewPager.adapter = DailyAdapter(childFragmentManager, requireContext(), tabLayoutDaily.tabCount)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayoutDaily))


        if (savedInstanceState != null) {
            onViewStateRestored(savedInstanceState)
        }

        setListeners()
        unHideBottomBar()
        setVisualEffects(view)
    }

    private fun getDate(after: Int): DateIdentificator {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, after)
        val data = calendar.time.toString().split(" ")
        return DateIdentificator(month = data[1], day = data[2].toInt())
    }

    private fun setListeners() {
        tabLayoutDaily.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                model.selectedDay.value = tab.position
//                previousPosition = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun setVisualEffects(view: View) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        exitTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            true
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            false
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        model.selectedDay.value?.let { tabLayoutDaily.getTabAt(it)?.select() }
    }
}
