package com.seagull.ui.daily

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seagull.data.model.DBSelectedRecipe
import com.seagull.data.model.MealTime
import com.seagull.data.model.Recipe
import com.seagull.data.source.repository.RecipeRepository
import kotlinx.coroutines.launch
import com.seagull.data.model.listBreakfast
import java.util.*

class DailyViewModel : ViewModel() {

    private val repository = RecipeRepository()
    private var fetched = false
    private var fetchedSelected = false

    val tabPosition = MutableLiveData<Int>()

    val recipes = MutableLiveData<List<Recipe>>()

    val breakfastIdList = List(7) {
        MutableLiveData<Recipe>()
    }

    val lunchIdList = List(7) {
        MutableLiveData<Recipe>()
    }

    val dinnerIdList = List(7) {
        MutableLiveData<Recipe>()
    }

    fun select(recipe: Recipe, mealTime: MealTime) {
        viewModelScope.launch {
            val date = getDate(tabPosition.value ?: 0)
            repository.updateSelectedRecipes(
                DBSelectedRecipe(
                    date = "$date.$mealTime",
                    id = recipe.id
                )
            )
            when (mealTime) {
                MealTime.Breakfast -> breakfastIdList[tabPosition.value ?: 0].value = recipe
                MealTime.Lunch -> lunchIdList[tabPosition.value ?: 0].value = recipe
                MealTime.Dinner -> dinnerIdList[tabPosition.value ?: 0].value = recipe
            }
        }
    }

    fun fetchRecipeListOnce() {
        if (fetched) {
            return
        }
        viewModelScope.launch {
            val list = repository.getRecipeList()
            if (!list.isNullOrEmpty()) {
                recipes.value = list
                println("Got from local")
                fetched = true
            } else {
                //temporary
                repository.fillRecipeList(*listBreakfast.toTypedArray())
                recipes.value = listBreakfast
                println("Got from remote")
                fetched = true
            }
        }
    }

    fun fetchSelectedListOnce() {
        if (fetchedSelected) {
            return
        }
        viewModelScope.launch {
            val list = repository.getSelectedList()
            if (!list.isNullOrEmpty()) {
                fetchedSelected = true

                //TODO better algorithm

                val map = mutableMapOf<String, Int>()
                //Jan.02.2021.Breakfast
                for (selected in list) {
                    map[selected.date] = selected.id
                }

                for (i in 0..6) {
                    val idBreakfast = map["${getDate(i)}.${MealTime.Breakfast}"]
                    if (idBreakfast != null) {
                        breakfastIdList[i].value = repository.getById(idBreakfast)
                    }

                    val idLunch = map["${getDate(i)}.${MealTime.Lunch}"]
                    if (idLunch != null) {
                        lunchIdList[i].value = repository.getById(idLunch)
                    }

                    val idDinner = map["${getDate(i)}.${MealTime.Dinner}"]
                    if (idDinner != null) {
                        dinnerIdList[i].value = repository.getById(idDinner)
                    }
                }
            }
        }
    }

    //time example: "Sat Jan 02 20:20:26 GMT 2021"
    private fun getDate(after: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, after)
        val data = calendar.time.toString().split(" ")
        return "${data[2]}.${data[1]}.${data[5]}"
        //02.Jan.2021
    }
}