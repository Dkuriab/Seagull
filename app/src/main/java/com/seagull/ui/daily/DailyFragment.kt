package com.seagull.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.misc.getSlideAnimatorWithStartDelay
import com.seagull.misc.navigate
import com.seagull.misc.unHideBottomBar
import java.util.*

class DailyFragment : Fragment() {
    private val model: DailyViewModel by activityViewModels()
    private var previousPosition = 0 //TODO remove to hell

    private lateinit var tabLayoutDaily: TabLayout
    private lateinit var breakfastCardTitle: TextView
    private lateinit var breakfastCard: ShapeableImageView
    private lateinit var lunchCardTitle: TextView
    private lateinit var lunchCard: ShapeableImageView
    private lateinit var dinnerCardTitle: TextView
    private lateinit var dinnerCard: ShapeableImageView
    private lateinit var all: ConstraintLayout

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

        setObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutDaily = view.findViewById(R.id.tab_layout_daily)
        breakfastCardTitle = view.findViewById(R.id.breakfast_card_title)
        lunchCardTitle = view.findViewById(R.id.lunch_card_title)
        dinnerCardTitle = view.findViewById(R.id.dinner_card_title)

        breakfastCard = view.findViewById(R.id.breakfast_card)
        lunchCard = view.findViewById(R.id.lunch_card)
        dinnerCard = view.findViewById(R.id.dinner_card)
        all = view.findViewById(R.id.all)

        for (i in 0..6) {
            tabLayoutDaily.addTab(
                tabLayoutDaily.newTab().setText(getDate(i).toString()).setTag(getDate(i))
            )
        }

        if (savedInstanceState != null) {
            onViewStateRestored(savedInstanceState)
        }

        setListeners()
        unHideBottomBar()
        setTransition(view)
    }

    //time example Sat Jan 02 20:20:26 GMT 2021
    private fun getDate(after: Int): DateIdentificator {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, after)
        val data = calendar.time.toString().split(" ")
        return DateIdentificator(data[1], data[2].toInt())
    }

    private fun setListeners() {
        breakfastCard.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToBreakfastListFragment())
        }

        lunchCard.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToLunchListFragment())
        }

        dinnerCard.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToDinnerListFragment())
        }

        tabLayoutDaily.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                model.tabPosition.value = tab.position

                if (tab.position > previousPosition) {
//                    all.startAnimation(inFromRight)
                    breakfastCard.startAnimation(getSlideAnimatorWithStartDelay(0.77F))
                    breakfastCardTitle.startAnimation(getSlideAnimatorWithStartDelay(0.77F))
                    lunchCard.startAnimation(getSlideAnimatorWithStartDelay(0.88F))
                    lunchCardTitle.startAnimation(getSlideAnimatorWithStartDelay(0.88F))
                    dinnerCard.startAnimation(getSlideAnimatorWithStartDelay(0.99F))
                    dinnerCardTitle.startAnimation(getSlideAnimatorWithStartDelay(0.99F))
                } else {
                    breakfastCard.startAnimation(getSlideAnimatorWithStartDelay(-0.77F))
                    breakfastCardTitle.startAnimation(getSlideAnimatorWithStartDelay(-0.77F))
                    lunchCard.startAnimation(getSlideAnimatorWithStartDelay(-0.88F))
                    lunchCardTitle.startAnimation(getSlideAnimatorWithStartDelay(-0.88F))
                    dinnerCard.startAnimation(getSlideAnimatorWithStartDelay(-0.99F))
                    dinnerCardTitle.startAnimation(getSlideAnimatorWithStartDelay(-0.99F))
                }

                previousPosition = tab.position
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

    private fun setObservers() {
        model.breakfastIdList[tabLayoutDaily.selectedTabPosition].observe(
            viewLifecycleOwner,
            { recipe ->
                breakfastCardTitle.text = recipe.name
            }
        )

        model.lunchIdList[tabLayoutDaily.selectedTabPosition].observe(
            viewLifecycleOwner,
            { recipe ->
                lunchCardTitle.text = recipe.name
            }
        )

        model.dinnerIdList[tabLayoutDaily.selectedTabPosition].observe(
            viewLifecycleOwner,
            { recipe ->
                dinnerCardTitle.text = recipe.name
            }
        )

        model.tabPosition.observe(
            viewLifecycleOwner,
            { tabPosition ->
                breakfastCardTitle.text =
                    model.breakfastIdList[tabPosition].value?.name ?: getString(R.string.breakfast)
                lunchCardTitle.text =
                    model.lunchIdList[tabPosition].value?.name ?: getString(R.string.lunch)
                dinnerCardTitle.text =
                    model.dinnerIdList[tabPosition].value?.name ?: getString(R.string.dinner)
            }
        )
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        model.tabPosition.value?.let { tabLayoutDaily.getTabAt(it)?.select() }
    }
}
