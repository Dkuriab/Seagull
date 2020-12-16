package com.seagull.ui.daily

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.seagull.R
import com.squareup.picasso.Picasso
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
        Picasso.get()
            .load(RecipeFragmentArgs.fromBundle(requireArguments()).recipePhotoLink)
            .placeholder(R.drawable.progress_animated)
            .into(recipe_picture)

        recipe_description.text =
            RecipeFragmentArgs.fromBundle(requireArguments()).recipeDescription

        super.onViewCreated(view, savedInstanceState)
        setTransition()
    }

    private fun setTransition() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragment_place
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            setAllContainerColors(Color.TRANSPARENT)
            scrimColor = Color.WHITE
            setPathMotion(MaterialArcMotion())
        }
    }

}