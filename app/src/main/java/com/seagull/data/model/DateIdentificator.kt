package com.seagull.data.model

class DateIdentificator(val month: String, val day: Int) {

    override fun toString(): String {
        return "$day $month"
    }
}
