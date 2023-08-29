package com.example.jeanjacquesbastien_countriesoftheworld.di

import com.example.jeanjacquesbastien_countriesoftheworld.network.CountryApiService
import com.example.jeanjacquesbastien_countriesoftheworld.network.CountryRepository
import com.example.jeanjacquesbastien_countriesoftheworld.network.CountryRepositoryImplementation
import com.example.jeanjacquesbastien_countriesoftheworld.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CountryModules {
    @Singleton
    @Provides
    fun providesServiceApi(): CountryApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkhttpClient())
            .build()
            .create(CountryApiService::class.java)

    @Singleton
    @Provides
    fun providesOkhttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply{
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesCountryRepository(): CountryRepository =
        CountryRepositoryImplementation(providesServiceApi())
}