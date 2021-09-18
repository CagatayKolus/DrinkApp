package com.cagataykolus.drinkapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.drinkapp.data.local.dao.DrinkDao
import com.cagataykolus.drinkapp.model.Drink

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
/**
 * DrinkDatabase provides DAO [DrinkDao] by using method [getDrinkDao].
 */
@Database(
    entities = [Drink::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class DrinkDatabase : RoomDatabase() {

    abstract fun getDrinkDao(): DrinkDao

    companion object {
        private const val DB_NAME = "database_drink"

        @Volatile
        private var INSTANCE: DrinkDatabase? = null

        fun getInstance(context: Context): DrinkDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrinkDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_DRINK).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
