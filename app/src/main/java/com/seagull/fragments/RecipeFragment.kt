package com.seagull.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seagull.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_fragment.*

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

        recipe_description.text = RecipeFragmentArgs.fromBundle(requireArguments()).recipeDescription
        super.onViewCreated(view, savedInstanceState)
    }
}