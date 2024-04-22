package com.app.shuru.data.network

import com.app.shuru.data.model.WeatherDto
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {
    @GET("data/2.5/weather?")
    suspend fun getWeatherData(
        @Query("q") q: String,
        @Query("appid") appid: String
    ): Response<WeatherDto.Response>

    @GET("data/2.5/weather?")
    suspend fun getWeatherDataCurrentLoc(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
    ): Response<WeatherDto.Response>
}