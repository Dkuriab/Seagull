package com.seagull.ui.daily

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.misc.navigate
import com.seagull.misc.unHideBottomBar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.daily_fragment.*
import java.util.*

//DateIdentificator in format  "MM_DD"
class DailyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_fragment, container, false)
    }

    private val model: SelectedRecipeViewModel by activityViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.breakfastIdList[tab_layout_daily.selectedTabPosition].observe(
            viewLifecycleOwner,
            { recipe ->
                breakfast_card_title.text = recipe.name
                Log.i("OnLiveDataChanged", "done")
            }
        )

        model.tabPosition.observe(
            viewLifecycleOwner,
            { tabPosition ->
                breakfast_card_title.text = model.breakfastIdList[tabPosition].value?.name ?: "Breakfast"
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        for (i in 0..6) {
//            val tab = tab_layout_daily.newTab()
//            tab.customView = layoutInflater.inflate(R.layout.item_tab, tab_layout_daily, false)
//            val date = getDate(i)
            tab_layout_daily.addTab(tab_layout_daily.newTab().setText(getDate(i).toString()).setTag(getDate(i)))
        }

//        tab_layout_daily.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.text = "selected"
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
////                tab?.text = "Unselected"
////                TODO("Not yet implemented")
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
////                tab?.text = "WasSelected"
////                TODO("Not yet implemented")
//            }
//
//        })

        if (savedInstanceState != null) {
            onViewStateRestored(savedInstanceState)
        }

        setListeners()
        unHideBottomBar()
        setTransition(view)
    }

    //time example Sat Jan 02 20:20:26 GMT 2021
    private fun getDate(after: Int) : DateIdentificator {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, after)
        val data = calendar.time.toString().split(" ")
        return DateIdentificator(data[1], data[2].toInt())
    }

    private fun setListeners() {
        breakfast_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToBreakfastListFragment())
        }

        lunch_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToLunchListFragment())
        }

        dinner_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToDinnerListFragment())
        }

        tab_layout_daily.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                model.tabPosition.value = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun setTransition(view: View) {
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        exitTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ true
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ false
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        model.tabPosition.value?.let { tab_layout_daily.getTabAt(it)?.select() }
    }
}
