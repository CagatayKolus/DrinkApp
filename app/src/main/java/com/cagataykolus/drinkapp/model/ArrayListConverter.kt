package com.cagataykolus.drinkapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toList(data: String?): List<String> {
            val listType = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromList(data: List<String>): String {
            val gson = Gson()
            return gson.toJson(data)
        }
    }
}