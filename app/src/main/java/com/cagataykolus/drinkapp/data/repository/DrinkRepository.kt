package com.cagataykolus.drinkapp.data.repository

import com.cagataykolus.drinkapp.data.local.dao.DrinkDao
import com.cagataykolus.drinkapp.data.remote.api.DrinkService
import com.cagataykolus.drinkapp.model.Drink
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import retrofit2.Response


/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
interface DrinkRepository {
    fun getAllDrinks(
    ): Flow<Resource<List<Drink>>>

    fun deleteAllDrinks(
    ): Flow<Resource<List<Drink>>>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultDrinkRepository @Inject constructor(
    private val dao: DrinkDao,
    private val service: DrinkService
) : DrinkRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllDrinks(
    ): Flow<Resource<List<Drink>>> {
        return object : NetworkBoundRepository<List<Drink>, List<Drink>>() {

            override suspend fun saveRemoteData(response: List<Drink>) = dao.addDrink(response)

            override fun fetchFromLocal(): Flow<List<Drink>> = dao.getAllDrinks()

            override suspend fun fetchFromRemote(): Response<List<Drink>> =
                service.getDrinkResult()

        }.asFlow()
    }
    /**
     * Deletes all data.
     */
    override fun deleteAllDrinks(): Flow<Resource<List<Drink>>> {
        return object : NetworkBoundRepository<List<Drink>, List<Drink>>() {

            override suspend fun saveRemoteData(response: List<Drink>) = dao.deleteAllDrinks()

            override fun fetchFromLocal(): Flow<List<Drink>> {
                return dao.getAllDrinks()
            }

            override suspend fun fetchFromRemote(): Response<List<Drink>> {
                return service.getDrinkResult()
            }

        }.asFlow()
    }
}