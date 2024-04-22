package com.diagnal.diagnaltask.di

import android.content.Context
import com.app.shuru.data.network.ApiInterface
import com.app.shuru.data.network.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
//        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(provideInterceptor())
        return httpClient.build()
    }
    private fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val requestBuilder = request.newBuilder()
//            requestBuilder.addHeader("Cookie", cookie)
            requestBuilder.method(request.method, request.body)
            request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideServiceGenerator(client: OkHttpClient): ApiInterface {
        return ServiceGenerator.getRetroFit(client)
    }


}