package com.seagull.ui.daily

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.MyApplication.Companion.appContext
import com.seagull.R
import com.seagull.misc.unHideBottomBar
import com.seagull.ui.MainActivity
import com.squareup.picasso.Picasso

class RecipeFragment : Fragment() {
    private lateinit var recipePicture : ImageView
    private lateinit var recipeDescription : TextView
    private lateinit var backButton : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recipe_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipePicture = view.findViewById(R.id.recipe_picture)
        recipeDescription = view.findViewById(R.id.recipe_description)
        backButton = view.findViewById(R.id.back_button)
        unHideBottomBar()

        Picasso.get()
            .load(RecipeFragmentArgs.fromBundle(requireArguments()).recipePhotoLink)
            .placeholder(R.drawable.progress_animated)
            .into(recipePicture)

        recipeDescription.text =
            RecipeFragmentArgs.fromBundle(requireArguments()).recipeDescription

        setTransition()
        setListeners()
    }

    private fun setListeners() {
        backButton.setOnClickListener{
            findNavController().navigateUp()
        }
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