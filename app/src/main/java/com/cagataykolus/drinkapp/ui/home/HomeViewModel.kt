package com.cagataykolus.drinkapp.ui.home

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataykolus.drinkapp.data.repository.DrinkRepository
import com.cagataykolus.drinkapp.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.cagataykolus.drinkapp.model.Drink

/**
 * Created by Çağatay Kölüş on 18.09.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val drinkRepository: DrinkRepository) :
    ViewModel() {
    private val _drinkLiveData = MutableLiveData<State<List<Drink>>>()
    val drinkLiveData: LiveData<State<List<Drink>>> = _drinkLiveData

    fun getDrink() {
        viewModelScope.launch {
            drinkRepository.deleteAllDrinks()
                .onStart {}
                .map {}
                .collect {}
        }

        val timer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                viewModelScope.launch {
                    drinkRepository.getAllDrinks()
                        .onStart { _drinkLiveData.value = State.loading() }
                        .map { resource -> State.fromResource(resource) }
                        .collect { state -> _drinkLiveData.value = state }
                }
            }
        }
        timer.start()
    }
}