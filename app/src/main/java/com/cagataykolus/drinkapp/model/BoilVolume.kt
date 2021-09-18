package com.cagataykolus.drinkapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class BoilVolume(
    val unit: String?,
    val value: Int?
)

class BoilVolumeConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toBoilVolume(data: String?): BoilVolume {
            if (data == null) {
                return BoilVolume("empty",0)
            }
            val listType = object : TypeToken<BoilVolume>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromBoilVolume(data: BoilVolume): String {
            return gson.toJson(data)
        }
    }
}