package com.cagataykolus.drinkapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Volume(
    val unit: String?,
    val value: Int?
)

class VolumeConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toBoilVolume(data: String?): Volume {
            if (data == null) {
                return Volume("empty", 0)
            }
            val listType = object : TypeToken<Volume>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromBoilVolume(data: Volume): String {
            return gson.toJson(data)
        }
    }
}