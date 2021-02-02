package com.seagull.mapper

import androidx.room.TypeConverter
import com.seagull.data.model.buyinglist.MeasureType

class MeasureTypeDBConverter {
    @TypeConverter
    fun toMeasureType(value: Int) = enumValues<MeasureType>()[value]

    @TypeConverter
    fun fromMeasureType(value: MeasureType) = value.ordinal
}