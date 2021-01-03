package com.seagull.ui.daily.choose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.Recipe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_preview.view.*

class RecipeListAdapter(
    private val recipes: List<Recipe>,
    private val onClick: (Int, View, Recipe) -> Unit,
    private val onLongClick: (View, Recipe) -> Unit,
    private val liveDataListener: (View, Recipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(val root: View) :
        RecyclerView.ViewHolder(root) {
        fun bind(recipe: Recipe, position: Int) {
            with(root) {
                transitionName = "${this.transitionName}${recipe.id}"
                photo.transitionName = "${this.photo.transitionName}${recipe.id}"
                name.transitionName = "${this.name.transitionName}${recipe.id}"
                name.text = recipe.name
                Picasso.get()
                    .load(recipe.link)
                    .placeholder(R.drawable.progress_animated)
                    .into(photo)
            }
            liveDataListener(root, recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val holder = RecipeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recipe_preview, parent, false)
        )
        holder.root.setOnClickListener {
            onClick(holder.adapterPosition, it, recipes[holder.adapterPosition])
        }
        holder.root.setOnLongClickListener {
            onLongClick(it, recipes[holder.adapterPosition])
            return@setOnLongClickListener true
        }
        return holder
    }

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) = holder.bind(recipes[position], position)

    override fun getItemCount(): Int {
        return recipes.size
    }
}