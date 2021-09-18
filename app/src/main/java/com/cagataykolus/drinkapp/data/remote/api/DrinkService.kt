package com.cagataykolus.drinkapp.data.remote.api

import com.cagataykolus.drinkapp.data.remote.api.DrinkService.Companion.DRINK_API_URL
import com.cagataykolus.drinkapp.model.Drink
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
/**
 * Service to fetch data using endpoint [DRINK_API_URL].
 */
interface DrinkService {
    @GET("random")
    suspend fun getDrinkResult(): Response<List<Drink>>

    companion object {
        const val DRINK_API_URL = "https://api.punkapi.com/v2/beers/"
    }
}