package com.cagataykolus.drinkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.drinkapp.model.Drink
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
/**
 * Data Access Object (DAO) for [com.cagataykolus.drinkapp.data.local.DrinkDatabase]
 */
@Dao
interface DrinkDao {
    /**
     * Inserts [drink] into the [Drink.TABLE_DRINK] table.
     * Duplicate values are replaced in the table.
     * @param drink
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDrink(drink: List<Drink>)

    /**
     * Fetches all the data from the [Drink.TABLE_DRINK] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Drink.TABLE_DRINK}")
    fun getAllDrinks(): Flow<List<Drink>>

    /**
     * Deletes all the data from the [Drink.TABLE_DRINK] table.
     */
    @Query("DELETE FROM ${Drink.TABLE_DRINK}")
    suspend fun deleteAllDrinks()
}