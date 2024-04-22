package com.app.shuru.data.repository

import com.app.shuru.data.model.WeatherDto
import com.app.shuru.data.network.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiInterface: ApiInterface) {


    suspend fun fetchWeatherCity(city: String, appId: String): Response<WeatherDto.Response> {
        return apiInterface.getWeatherData(city, appId)
    }

    suspend fun fetchWeatherCurrentLoc(lat: String, long: String,appId: String): Response<WeatherDto.Response> {
        return apiInterface.getWeatherDataCurrentLoc(lat, long, appId)
    }
}