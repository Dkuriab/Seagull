package com.seagull.ui.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seagull.data.model.Recipe

class SelectedRecipeViewModel : ViewModel() {

//    val selectedBreakfastId: MutableLiveData<Recipe> by lazy {
//        MutableLiveData<Recipe>()
//    }
//    val selectedBreakfastId = MutableLiveData<Recipe>()

    val breakfastIdList = List(7) {
        MutableLiveData<Recipe>()
    }

    val tabPosition = MutableLiveData<Int>()

    val selectedLunchId = MutableLiveData<Int>()
    val selectedDinnerId = MutableLiveData<Int>()

}