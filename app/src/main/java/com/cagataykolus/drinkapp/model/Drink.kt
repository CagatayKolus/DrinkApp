package com.cagataykolus.drinkapp.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cagataykolus.drinkapp.model.Drink.Companion.TABLE_DRINK
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_DRINK)
@TypeConverters(
    BoilVolumeConverter::class,
    ListConverter::class,
    IngredientsConverter::class,
    MethodConverter::class,
    VolumeConverter::class
)
data class Drink(
    @PrimaryKey
    @NonNull
    val id: Int?,
    val abv: Double?,
    val attenuation_level: Double?,
    val boil_volume: BoilVolume?,
    val brewers_tips: String?,
    val contributed_by: String?,
    val description: String?,
    val ebc: Double?,
    val first_brewed: String?,
    val food_pairing: List<String>?,
    val ibu: Int?,
    val image_url: String?,
    val ingredients: Ingredients?,
    val method: Method?,
    val name: String?,
    val ph: Double?,
    val srm: Double?,
    val tagline: String?,
    val target_fg: Int?,
    val target_og: Int?,
    val volume: Volume?,
)  {
    companion object {
        const val TABLE_DRINK = "table_drink"
    }
}