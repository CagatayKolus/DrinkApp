package com.cagataykolus.drinkapp.di.module

import android.app.Application
import com.cagataykolus.drinkapp.data.local.DrinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class DrinkDatabaseModule {
    @Singleton
    @Provides
    fun provideDrinkDatabase(application: Application) = DrinkDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideDrinkDao(database: DrinkDatabase) = database.getDrinkDao()
}