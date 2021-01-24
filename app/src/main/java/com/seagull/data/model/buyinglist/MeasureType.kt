package com.seagull.data.model.buyinglist

import com.seagull.MyApplication.Companion.appContext
import com.seagull.R

enum class MeasureType {
    KILOGRAMS,
    GRAMS,
    ITEMS;


    override fun toString(): String {
        return when (this) {
            KILOGRAMS -> appContext.getString(R.string.kilograms)
            GRAMS -> appContext.getString(R.string.grams)
            ITEMS -> appContext.getString(R.string.items)
        }
    }
}