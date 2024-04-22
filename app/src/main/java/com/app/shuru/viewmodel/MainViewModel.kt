package com.app.shuru.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.shuru.data.model.WeatherDto
import com.app.shuru.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _weather = MutableLiveData<WeatherDto.Response>()
    val weather: LiveData<WeatherDto.Response> = _weather

    private val _weatherCurrent = MutableLiveData<WeatherDto.Response>()
    val weatherCurrent: LiveData<WeatherDto.Response> = _weatherCurrent


    fun fetchWeather(city: String, appId: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchWeatherCity(city, appId)
                if (response.isSuccessful) {
                    val resp = response.body()
                    _weather.value = resp!!
                } else {
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun fetchWeatherCurrentLoc(lat: String, long: String,appId: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchWeatherCurrentLoc(lat, long, appId)
                if (response.isSuccessful) {
                    val resp = response.body()
                    _weatherCurrent.value = resp!!
                } else {
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}