package com.cagataykolus.drinkapp.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cagataykolus.drinkapp.model.Drink

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object MigrationDatabase {
    const val DB_VERSION = 2

    val MIGRATION_DRINK: Array<Migration>
        get() = arrayOf(
            migrationDRINK()
        )

    /**
     *  Creates a new migration between version 1 and 2 for [Drink.TABLE_DRINK] table.
     */
    private fun migrationDRINK(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Drink.TABLE_DRINK} ADD COLUMN body TEXT")
        }
    }
}
