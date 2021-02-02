package com.seagull.ui.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.misc.navigate
import com.seagull.misc.unHideBottomBar

class DailyContentFragment : Fragment() {
    private val model: DailyViewModel by activityViewModels()

    private lateinit var breakfastCardTitle: TextView
    private lateinit var breakfastCard: ShapeableImageView
    private lateinit var lunchCardTitle: TextView
    private lateinit var lunchCard: ShapeableImageView
    private lateinit var dinnerCardTitle: TextView
    private lateinit var dinnerCard: ShapeableImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breakfastCardTitle = view.findViewById(R.id.breakfast_card_title)
        lunchCardTitle = view.findViewById(R.id.lunch_card_title)
        dinnerCardTitle = view.findViewById(R.id.dinner_card_title)

        breakfastCard = view.findViewById(R.id.breakfast_card)
        lunchCard = view.findViewById(R.id.lunch_card)
        dinnerCard = view.findViewById(R.id.dinner_card)

        if (savedInstanceState != null) {
            onViewStateRestored(savedInstanceState)
        }

        setListeners()
        unHideBottomBar()
        setTransition(view)
    }

    private fun setListeners() {
        breakfastCard.setOnClickListener {
            parentFragment?.navigate(DailyFragmentDirections.actionDailyFragmentToBreakfastListFragment())
        }

        lunchCard.setOnClickListener {
            parentFragment?.navigate(DailyFragmentDirections.actionDailyFragmentToLunchListFragment())
        }

        dinnerCard.setOnClickListener {
            parentFragment?.navigate(DailyFragmentDirections.actionDailyFragmentToDinnerListFragment())
        }

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
        model.breakfastIdList[model.tabPosition.value ?: 0].observe(
            viewLifecycleOwner,
            { recipe ->
                breakfastCardTitle.text = recipe.name
            }
        )

        model.lunchIdList[model.tabPosition.value ?: 0].observe(
            viewLifecycleOwner,
            { recipe ->
                lunchCardTitle.text = recipe.name
            }
        )

        model.dinnerIdList[model.tabPosition.value ?: 0].observe(
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
}
