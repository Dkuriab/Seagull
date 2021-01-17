package com.seagull.ui.daily.choose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(
    private var recipes: List<Recipe>,
    private val onClick: (View, Recipe) -> Unit,
    private val onLongClick: (Recipe) -> Unit,
    private val liveDataListener: (View, Recipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(val root: View) :
        RecyclerView.ViewHolder(root) {
        private val photo = root.findViewById<ImageView>(R.id.photo)
        private val name = root.findViewById<TextView>(R.id.name)

        fun bind(recipe: Recipe) {
            with(root) {
                transitionName = "${this.transitionName}${recipe.id}"
                photo.transitionName = "${photo.transitionName}${recipe.id}"
                name.transitionName = "${name.transitionName}${recipe.id}"
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
            onClick(it, recipes[holder.adapterPosition])
        }
        holder.root.setOnLongClickListener {
            onLongClick(recipes[holder.adapterPosition])
            return@setOnLongClickListener true
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

    fun putRecipes(list: List<Recipe>) {
        recipes = list
        notifyDataSetChanged()
    }
}