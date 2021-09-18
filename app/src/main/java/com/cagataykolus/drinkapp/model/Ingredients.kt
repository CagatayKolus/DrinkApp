package com.cagataykolus.drinkapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Ingredients(
    val hops: List<Hop>?,
    val malt: List<Malt>?,
    val yeast: String?
)

class IngredientsConverter {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toIngredients(data: String?): Ingredients {
            if (data == null) {
                return Ingredients(listOf(),listOf(),"empty")
            }
            val listType = object : TypeToken<Ingredients>() {}.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun fromIngredients(data: Ingredients): String {
            return gson.toJson(data)
        }
    }
}