package com.cagataykolus.drinkapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Method(
    val fermentation: Fermentation?,
    val mash_temp: List<MashTemp>?,
    val twist: String?
)

class MethodConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toBoilVolume(data: String?): Method {
            if (data == null) {
                return Method(Fermentation(Temp("empty", 0)), listOf(), "empty")
            }
            val listType = object : TypeToken<Method>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromBoilVolume(data: Method): String {
            return gson.toJson(data)
        }
    }
}