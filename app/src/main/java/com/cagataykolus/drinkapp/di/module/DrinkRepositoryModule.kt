package com.cagataykolus.drinkapp.di.module

import com.cagataykolus.drinkapp.data.repository.DefaultDrinkRepository
import com.cagataykolus.drinkapp.data.repository.DrinkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class DrinkRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDrinkRepository(repository: DefaultDrinkRepository): DrinkRepository
}