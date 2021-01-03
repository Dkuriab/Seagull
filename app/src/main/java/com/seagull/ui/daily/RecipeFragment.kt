package com.seagull.ui.daily

import android.graphics.Color
import android.graphics.Interpolator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.misc.slideUp
import com.seagull.misc.unHideBottomBar
import com.seagull.ui.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.recipe_fragment.*
import kotlinx.android.synthetic.main.recipe_preview.*

class RecipeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unHideBottomBar()

        Picasso.get()
            .load(RecipeFragmentArgs.fromBundle(requireArguments()).recipePhotoLink)
            .placeholder(R.drawable.progress_animated)
            .into(recipe_picture)

        recipe_description.text =
            RecipeFragmentArgs.fromBundle(requireArguments()).recipeDescription

        setTransition()
    }

    private fun setTransition() {

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragment_place
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            scrimColor = Color.WHITE
            interpolator = LinearOutSlowInInterpolator()
            setPathMotion(MaterialArcMotion())
        }

        exitTransition = MaterialSharedAxis(
            MaterialSharedAxis.Y,
            /* forward= */ true
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

        reenterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Y,
            /* forward= */ false
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }

    }

}