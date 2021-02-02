package com.seagull.ui.buyinglist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BuyingListViewModel : ViewModel() {
    val selectedDates = List(7) {
        MutableLiveData<Boolean>()
    }

    val boughtIngredients = MutableLiveData<HashSet<String>>()

    fun changeDateStatus(position: Int) {
        selectedDates[position].value = selectedDates[position].value != true
    }

    fun changeIngredientStatus(name: String) {
        println(name)
        if (boughtIngredients.value != null && boughtIngredients.value!!.contains(name)) {
            boughtIngredients.value!!.remove(name)
        } else {
            if (boughtIngredients.value == null) {
                boughtIngredients.value = hashSetOf(name)
            } else {
                boughtIngredients.value!!.add(name)
            }
        }
    }

}