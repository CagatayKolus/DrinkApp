package com.cagataykolus.drinkapp.di.module

import com.cagataykolus.drinkapp.data.remote.api.DrinkService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class DrinkApiModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): DrinkService = Retrofit.Builder()
        .baseUrl(DrinkService.DRINK_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(DrinkService::class.java)
}
