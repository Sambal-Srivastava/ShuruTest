package com.app.shuru.data.model

import com.google.gson.annotations.SerializedName

object WeatherDto {


    data class Response(
        @SerializedName("main") var main: Main? = null
    )

    data class Main(
        @SerializedName("temp") var temp: Double = 0.0,
        @SerializedName("feels_like") var feelsLike: Double = 0.0,
        @SerializedName("temp_min") var tempMin: Double = 0.0,
        @SerializedName("temp_max") var tempMax: Double = 0.0,
        @SerializedName("pressure") var pressure: Double = 0.0,
        @SerializedName("humidity") var humidity: Double = 0.0
    )
}