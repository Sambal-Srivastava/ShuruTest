package com.app.shuru.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ServiceGenerator @Inject constructor() {
    companion object {
        private lateinit var retrofit: Retrofit
        fun getRetroFit(client: OkHttpClient): ApiInterface {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }

    }
}