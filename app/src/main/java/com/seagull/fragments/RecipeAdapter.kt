package com.seagull.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_preview.view.*

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(recipe: Recipe) {
            with (root) {
                name.text = recipe.name
                Picasso.get()
                    .load(recipe.link)
                    .placeholder(R.drawable.progress_animated)
                    .into(photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val holder = RecipeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recipe_preview, parent, false)
        )
        holder.root.setOnClickListener {
            onClick(recipes[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) = holder.bind(recipes[position])

    override fun getItemCount(): Int {
        return recipes.size
    }
}